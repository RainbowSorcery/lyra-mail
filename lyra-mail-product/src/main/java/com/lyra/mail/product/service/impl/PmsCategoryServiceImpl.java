package com.lyra.mail.product.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.entity.vo.Catalog2VO;
import com.lyra.mail.product.mapper.PmsCategoryMapper;
import com.lyra.mail.product.service.IPmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.product.service.PmsCategoryBranRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements IPmsCategoryService {
    @Autowired
    private PmsCategoryMapper categoryMapper;

    private static final String REDIS_CACHE_CATEGORY = "category";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    
    private Logger logger = LoggerFactory.getLogger(IPmsCategoryService.class);

    @Autowired
    private PmsCategoryBranRelationService categoryBranRelationService;

    @Override
    public List<PmsCategory> categoryListByTree() {
        List<PmsCategory> pmsCategories = categoryMapper.selectList(null);

        return pmsCategories
                .stream()
                .filter((category -> category.getParentCid() == 0))
                .peek((category -> category.setChildren(getChildren(category, pmsCategories))))
                .sorted((Comparator.comparingInt(category -> (category.getSort() == null ? 0 : category.getSort()))))
                .collect(Collectors.toList());
    }

    private List<PmsCategory> getChildren(PmsCategory root, List<PmsCategory> allCategory) {
        List<PmsCategory> children = allCategory
                .stream()
                .filter((category -> Objects.equals(root.getCatId(), category.getParentCid())))
                .peek((category -> {
                    category.setChildren(this.getChildren(category, allCategory));
                }))
                .sorted((Comparator.comparingInt(category -> (category.getSort() == null ? 0 : category.getSort()))))
                .collect(Collectors.toList());

        return children;
    }

    @Override
    @Transactional
    public void logicDeleteByIds(List<Long> categoryIds) {
        // todo 判断是否有其他地方引用了category

        categoryMapper.deleteBatchIds(categoryIds);

//        categoryMapper.deleteBatchIds(categoryIds);
    }

    @Override
    public void updateDetails(PmsCategory pmsCategory) {
        // todo 要进行更新操作时 中间表的冗余字段也要进行更新
        categoryMapper.updateById(pmsCategory);

        if (StringUtils.isNotEmpty(pmsCategory.getName())) {
            categoryBranRelationService.updateBrandName(pmsCategory.getCatId(), pmsCategory.getName());
        }
    }

    @Override
    public List<PmsCategory> findCategoryByFirstCategory() {
        QueryWrapper<PmsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_cid", 0);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, List<Catalog2VO>> getCatalogJson() throws JsonProcessingException {
        // 从redis进行进行获取数据
        String categoryMapStrings = redisTemplate.opsForValue().get(REDIS_CACHE_CATEGORY);
        // 判断数据是否存在
        if (StringUtils.isEmpty(categoryMapStrings)) {
            Map<String, List<Catalog2VO>> catalogJsonResult = getCatalogJsonResult();
            // 进行数据序列化
            categoryMapStrings = objectMapper.writeValueAsString(catalogJsonResult);
            redisTemplate.opsForValue().set(REDIS_CACHE_CATEGORY, categoryMapStrings);
        }

        // 进行数据反序列化 参数2可以对复杂类型进行反序列化
        return objectMapper.readValue(categoryMapStrings, new TypeReference<Map<String, List<Catalog2VO>>>() {
        });
    }

    public Map<String, List<Catalog2VO>> getCatalogJsonResult() throws JsonProcessingException {
        /*
        * 加锁流程:
        * 1. 判断redis是否有缓存数据
        * 2. 若有缓存数据 直接反序列化对象并进行返回
        *    若缓存中无数据 则进行数据库查询 并将缓存结果保存至redis中
        * */

        // 因为在springboot 容器中的bean都是单例 所以只要锁住当前对象即可
        synchronized (this) {
            String categoryCache = redisTemplate.opsForValue().get(REDIS_CACHE_CATEGORY);

            if (StringUtils.isEmpty(categoryCache)) {
                System.out.println("数据库查询");

                List<PmsCategory> categories = categoryMapper.selectList(null);
                // 查询一级分类
                List<PmsCategory> categoryByFirstCategory = getParentCid(categories, 0L);
                // 数据库查询业务逻辑
                Map<String, List<Catalog2VO>> categoryMap = categoryByFirstCategory.stream().collect(Collectors.toMap((item) -> item.getCatId().toString(), (item) -> {
                    List<PmsCategory> category2List = getParentCid(categories, item.getCatId());

                    // 根据一级分类id 查询二级分类
                    return category2List.stream().map((category2) -> {
                        Catalog2VO catalog2VO = new Catalog2VO();
                        catalog2VO.setCatalog1Id(item.getCatId().toString());
                        catalog2VO.setId(category2.getCatId().toString());
                        catalog2VO.setName(category2.getName());

                        List<PmsCategory> category3List = getParentCid(categories, category2.getCatId());

                        // 根据二级分类id 查询三级分类
                        List<Catalog2VO.Catalog3> catalog3s = category3List.stream().map((category3) -> {
                            Catalog2VO.Catalog3 catalog3 = new Catalog2VO.Catalog3();
                            catalog3.setCatalog2Id(category3.getParentCid().toString());
                            catalog3.setId(category3.getCatId().toString());
                            catalog3.setName(category3.getName());
                            return catalog3;
                        }).collect(Collectors.toList());
                        catalog2VO.setCatalog3List(catalog3s);

                        return catalog2VO;
                    }).collect(Collectors.toList());
                }));

                // 将缓存存入redis中
                redisTemplate.opsForValue().set(REDIS_CACHE_CATEGORY, objectMapper.writeValueAsString(categoryMap));

                return categoryMap;
            } else {
                // 若缓存存在 则直接反序列对象并进行返回
                return objectMapper.readValue(categoryCache, new TypeReference<Map<String, List<Catalog2VO>>>() {});
            }
        }
    }
    
    private List<PmsCategory> getParentCid(List<PmsCategory> categories, Long parentCid) {
        return categories.stream().filter((item) -> Objects.equals(item.getParentCid(), parentCid)).collect(Collectors.toList());
    }
}
