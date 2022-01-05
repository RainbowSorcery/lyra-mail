package com.lyra.mail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.enums.AttrType;
import com.lyra.mail.product.entity.PmsAttr;
import com.lyra.mail.product.entity.PmsAttrAttrgroupRelation;
import com.lyra.mail.product.entity.PmsAttrGroup;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.entity.vo.BaseListVO;
import com.lyra.mail.product.entity.vo.PmsAttrVO;
import com.lyra.mail.product.mapper.PmsAttrAttrgroupRelationMapper;
import com.lyra.mail.product.mapper.PmsAttrGroupMapper;
import com.lyra.mail.product.mapper.PmsAttrMapper;
import com.lyra.mail.product.mapper.PmsCategoryMapper;
import com.lyra.mail.product.service.IPmsAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr> implements IPmsAttrService {
    @Autowired
    private PmsAttrMapper attrMapper;

    @Autowired
    private PmsAttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    private PmsCategoryMapper categoryMapper;

    @Autowired
    private PmsAttrGroupMapper attrGroupMapper;

    @Override
    @Transactional
    public void saveAttrVo(PmsAttrVO attrVO) {
        PmsAttr attr = new PmsAttr();
        BeanUtils.copyProperties(attrVO, attr);
        attrMapper.insert(attr);

        PmsAttrAttrgroupRelation attrAttrgroupRelation = new PmsAttrAttrgroupRelation();
        // 可以从PmsAttr对象中获取到id
        attrAttrgroupRelation.setAttrId(attr.getAttrId());
        attrAttrgroupRelation.setAttrGroupId(attrVO.getAttrGroupId());
        attrAttrgroupRelationMapper.insert(attrAttrgroupRelation);
    }

    @Override
    public IPage<BaseListVO> baseList(Long categoryId, Integer pageSize, Integer current, String keyword, String attrType) {
        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();

        if (Objects.equals(attrType, "sale")) {
            queryWrapper.eq("attr_type", AttrType.saleType.getType());
        }else {
            queryWrapper.eq("attr_type", AttrType.baseType.getType());
        }

        queryWrapper.eq("catelog_id", categoryId);
        if (!StringUtils.isNullOrEmpty(keyword)) {
            queryWrapper.or().eq("attr_id", keyword);
            queryWrapper.or().like("attr_name", keyword);
        }

        IPage<PmsAttr> pmsAttrIPage = new Page<>(current, pageSize);
        attrMapper.selectPage(pmsAttrIPage, queryWrapper);

        List<PmsAttr> records = pmsAttrIPage.getRecords();

        List<BaseListVO> baseListVOS = new ArrayList<>();

        records.forEach((record) -> {
            PmsCategory pmsCategory = null;
            if (record.getCatelogId() != null) {
                pmsCategory = categoryMapper.selectById(record.getCatelogId());
            }

            PmsAttrAttrgroupRelation attrAttrgroupRelation = null;

            if (record.getAttrId() != null) {
                QueryWrapper<PmsAttrAttrgroupRelation> attrAttrgroupRelationQueryWrapper = new QueryWrapper<>();
                attrAttrgroupRelationQueryWrapper.eq("attr_id", record.getAttrId());
                attrAttrgroupRelation =  attrAttrgroupRelationMapper.selectOne(attrAttrgroupRelationQueryWrapper);
            }

            PmsAttrGroup attrGroup = null;
            if (attrAttrgroupRelation != null) {
                Long attrGroupId = attrAttrgroupRelation.getAttrGroupId();
                attrGroup = attrGroupMapper.selectById(attrGroupId);
            }

            BaseListVO baseListVO = new BaseListVO();
            BeanUtils.copyProperties(record, baseListVO);
            if (pmsCategory != null) {
                baseListVO.setCategoryName(pmsCategory.getName());
            }
            if (attrGroup != null) {
                baseListVO.setAttrGroupName(attrGroup.getAttrGroupName());
            }

            baseListVOS.add(baseListVO);
        });

        IPage<BaseListVO> baseListVOIPage = new Page<>();
        baseListVOIPage.setRecords(baseListVOS);
        baseListVOIPage.setPages(pmsAttrIPage.getPages());
        baseListVOIPage.setCurrent(pmsAttrIPage.getCurrent());
        baseListVOIPage.setSize(pmsAttrIPage.getSize());
        baseListVOIPage.setTotal(pmsAttrIPage.getTotal());

        return baseListVOIPage;
    }

    @Override
    public BaseListVO info(Long attrId) {
        PmsAttr attr = attrMapper.selectById(attrId);

        BaseListVO baseListVO = new BaseListVO();
        BeanUtils.copyProperties(attr, baseListVO);

        PmsCategory pmsCategory = categoryMapper.selectById(attr.getCatelogId());
        List<Long> categoryList = new ArrayList<>();
        getCategoryPath(categoryList, pmsCategory);

        Collections.reverse(categoryList);
        baseListVO.setCategoryIdPathList(categoryList);

        QueryWrapper<PmsAttrAttrgroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", baseListVO.getAttrId());
        PmsAttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.selectOne(queryWrapper);

        if (attrAttrgroupRelation != null) {
            baseListVO.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
        }

        return baseListVO;
    }

    @Override
    @Transactional
    public void updateAttr(BaseListVO baseListVO) {
        PmsAttr attr = new PmsAttr();
        BeanUtils.copyProperties(baseListVO, attr);

        attrMapper.updateById(attr);

        PmsAttrAttrgroupRelation attrAttrgroupRelation = new PmsAttrAttrgroupRelation();
        BeanUtils.copyProperties(baseListVO, attrAttrgroupRelation);
        QueryWrapper<PmsAttrAttrgroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", baseListVO.getAttrId());
        attrAttrgroupRelationMapper.update(attrAttrgroupRelation, queryWrapper);
    }

    private void getCategoryPath(List<Long> categoryPath, PmsCategory category) {
        if (category != null ) {
            categoryPath.add(category.getCatId());
            getCategoryPath(categoryPath, categoryMapper.selectById(category.getParentCid()));
        }
    }
}
