package com.lyra.mail.product.service;

import com.lyra.mail.product.entity.PmsSkuImages;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku图片 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsSkuImagesService extends IService<PmsSkuImages> {

    List<PmsSkuImages> getSkuImagesBySkuId(Long skuId);
}
