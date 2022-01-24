package com.lyra.mail.ware.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyra.mail.ware.entity.WmsPurchaseDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyra.mail.ware.entity.vo.PurchaseMergeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lyra
 * @since 2022-01-22
 */
public interface IWmsPurchaseDetailService extends IService<WmsPurchaseDetail> {

    IPage<WmsPurchaseDetail> findPurchaseList(Integer current, Integer pageSize, String key, Integer status, Long wareId);

    void merge(PurchaseMergeVO mergeVO);

    List<WmsPurchaseDetail> getPurchaseIdByPurchaseDetail(Long id);
}
