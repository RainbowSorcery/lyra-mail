package com.lyra.mail.ware.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.common.to.WareSkuHasStockTO;
import com.lyra.mail.ware.entity.WmsWareSku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<WareSkuHasStockTO> skuIdsHasStock(List<Long> skuIds);
}
