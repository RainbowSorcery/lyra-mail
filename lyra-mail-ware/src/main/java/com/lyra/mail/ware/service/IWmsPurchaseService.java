package com.lyra.mail.ware.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.ware.entity.WmsPurchase;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.ware.entity.vo.PurchaseDoneVO;

import java.util.List;

/**
 * <p>
 * 采购信息 服务类
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
public interface IWmsPurchaseService extends IService<WmsPurchase> {

    IPage<WmsPurchase> findPurchaseList(Integer current, Integer pageSize, String key, Integer status);

    List<WmsPurchase> undeceiveList();

    void received(List<Long> ids);

    void done(PurchaseDoneVO purchaseDoneVO);

}
