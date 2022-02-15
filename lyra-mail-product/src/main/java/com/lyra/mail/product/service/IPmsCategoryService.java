package com.lyra.mail.product.service;

import com.lyra.mail.product.entity.PmsCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.product.entity.vo.Catalog2VO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsCategoryService extends IService<PmsCategory> {

    List<PmsCategory> categoryListByTree();

    void logicDeleteByIds(List<Long> categoryIds);

    void updateDetails(PmsCategory pmsCategory);

    /**
     * 查询以及分类
     * @return 一级分类列表
     */
    List<PmsCategory> findCategoryByFirstCategory();

    Map<String, List<Catalog2VO>> getCatalogJson();
}
