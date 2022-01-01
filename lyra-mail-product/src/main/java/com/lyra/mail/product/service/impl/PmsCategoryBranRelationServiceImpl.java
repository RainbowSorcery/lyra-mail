package com.lyra.mail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.common.exception.LyraMailException;
import com.lyra.mail.product.entity.PmsBrand;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.entity.PmsCategoryBranRelation;
import com.lyra.mail.product.mapper.PmsBrandMapper;
import com.lyra.mail.product.mapper.PmsCategoryBranRelationMapper;
import com.lyra.mail.product.mapper.PmsCategoryMapper;
import com.lyra.mail.product.service.PmsCategoryBranRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmsCategoryBranRelationServiceImpl extends ServiceImpl<PmsCategoryBranRelationMapper, PmsCategoryBranRelation> implements PmsCategoryBranRelationService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    @Autowired
    private PmsCategoryBranRelationMapper categoryBranRelationMapper;


    @Override
    public void categoryBrandRelation(PmsCategoryBranRelation pmsCategoryBranRelation) {
        QueryWrapper<PmsBrand> brandQueryWrapper = new QueryWrapper<>();
        brandQueryWrapper.eq("brand_id", pmsCategoryBranRelation.getBrandId());
        PmsBrand pmsBrand = pmsBrandMapper.selectOne(brandQueryWrapper);

        if (pmsBrand == null) {
            throw new LyraMailException("品牌不存在");
        }

        QueryWrapper<PmsCategory> pmsCategoryQueryWrapper = new QueryWrapper<>();
        pmsCategoryQueryWrapper.eq("cat_id", pmsCategoryBranRelation.getCatelogId());
        PmsCategory pmsCategory = pmsCategoryMapper.selectOne(pmsCategoryQueryWrapper);

        if (pmsCategory == null) {
            throw new LyraMailException("分类不存在");
        }

        pmsCategoryBranRelation.setBrandName(pmsBrand.getName());
        pmsCategoryBranRelation.setCatelogName(pmsCategory.getName());

        categoryBranRelationMapper.insert(pmsCategoryBranRelation);
    }

    @Override
    public void updateCategoryName(Long brandId, String name) {
        QueryWrapper<PmsCategoryBranRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId);
        PmsCategoryBranRelation categoryBranRelation = new PmsCategoryBranRelation();
        categoryBranRelation.setBrandName(name);
        categoryBranRelationMapper.update(categoryBranRelation, queryWrapper);
    }

    @Override
    public void updateBrandName(Long catId, String name) {
        QueryWrapper<PmsCategoryBranRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id", catId);
        PmsCategoryBranRelation categoryBranRelation = new PmsCategoryBranRelation();
        categoryBranRelation.setCatelogName(name);
        categoryBranRelationMapper.update(categoryBranRelation, queryWrapper);
    }
}
