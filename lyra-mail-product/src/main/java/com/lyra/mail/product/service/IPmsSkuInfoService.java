package com.lyra.mail.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.product.entity.PmsSkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * sku信息 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsSkuInfoService extends IService<PmsSkuInfo> {

    IPage<PmsSkuInfo> skuPageList(Integer current, Integer pageSize, Long catelogId, Long brandId, Integer min, Integer max, String key);
}
