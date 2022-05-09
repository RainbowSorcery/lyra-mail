package com.lyra.mail.product.service;

import com.lyra.mail.product.entity.PmsSkuSaleAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.product.entity.vo.ItemVO;

import java.util.List;

/**
 * <p>
 * sku销售属性&值 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsSkuSaleAttrValueService extends IService<PmsSkuSaleAttrValue> {

    List<ItemVO.skuItemSaleAttrVO> getSaleAttrsBySpuId(Long spuId);
}
