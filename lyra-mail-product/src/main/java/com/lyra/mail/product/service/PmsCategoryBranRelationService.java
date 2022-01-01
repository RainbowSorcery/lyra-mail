package com.lyra.mail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.product.entity.PmsCategoryBranRelation;

public interface PmsCategoryBranRelationService extends IService<PmsCategoryBranRelation> {
    void categoryBrandRelation(PmsCategoryBranRelation pmsCategoryBranRelation);

    void updateCategoryName(Long brandId, String name);

    void updateBrandName(Long catId, String name);

}
