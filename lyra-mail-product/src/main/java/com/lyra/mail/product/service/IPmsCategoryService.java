package com.lyra.mail.product.service;

import com.lyra.mail.product.entity.PmsCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
