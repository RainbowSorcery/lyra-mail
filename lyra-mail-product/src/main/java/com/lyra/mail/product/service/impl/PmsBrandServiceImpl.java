package com.lyra.mail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyra.mail.common.exception.LyraMailException;
import com.lyra.mail.product.entity.PmsBrand;
import com.lyra.mail.product.entity.PmsCategory;
import com.lyra.mail.product.entity.PmsCategoryBranRelation;
import com.lyra.mail.product.mapper.PmsBrandMapper;
import com.lyra.mail.product.mapper.PmsCategoryBranRelationMapper;
import com.lyra.mail.product.mapper.PmsCategoryMapper;
import com.lyra.mail.product.service.IPmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.product.service.PmsCategoryBranRelationService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements IPmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    private PmsCategoryBranRelationService categoryBranRelationService;

    @Override
    public void updateDetails(PmsBrand brand) {
        pmsBrandMapper.updateById(brand);
        // todo 要进行更新操作时 中间表的冗余字段也要进行更新
        if (!StringUtils.isNullOrEmpty(brand.getName())) {
            categoryBranRelationService.updateCategoryName(brand.getBrandId(), brand.getName());
        }
    }
}
