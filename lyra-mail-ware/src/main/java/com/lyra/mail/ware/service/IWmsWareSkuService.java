package com.lyra.mail.ware.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.ware.entity.WmsWareSku;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品库存 服务类
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
public interface IWmsWareSkuService extends IService<WmsWareSku> {

    IPage<WmsWareSku> findWareSkuList(Integer current, Integer pageSize, Long skuId, Long wareId);

    void addStock(Long skuId, Long wareId, Integer skuNum);
}
