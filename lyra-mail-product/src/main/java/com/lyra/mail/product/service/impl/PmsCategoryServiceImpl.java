package com.lyra.mail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.mapper.PmsCategoryMapper;
import com.lyra.mail.product.service.IPmsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<PmsCategory> categoryListByTree() {
        List<PmsCategory> pmsCategories = categoryMapper.selectList(null);

        List<PmsCategory> treeCategoryList = pmsCategories
                .stream()
                .filter((category -> category.getParentCid() == 0))
                .peek((category -> category.setChildren(getChildren(category, pmsCategories))))
                .collect(Collectors.toList());

        return treeCategoryList;
    }

    private List<PmsCategory> getChildren(PmsCategory root, List<PmsCategory> allCategory) {
        List<PmsCategory> children = allCategory
                .stream()
                .filter((category -> Objects.equals(root.getCatId(), category.getParentCid())))
                .peek((category -> {
                    category.setChildren(this.getChildren(category, allCategory));
                })).collect(Collectors.toList());

        return children;
    }

    @Override
    @Transactional
    public void logicDeleteByIds(List<Long> categoryIds) {
        // todo 判断是否有其他地方引用了category

        categoryMapper.deleteBatchIds(categoryIds);

//        categoryMapper.deleteBatchIds(categoryIds);
    }


}
