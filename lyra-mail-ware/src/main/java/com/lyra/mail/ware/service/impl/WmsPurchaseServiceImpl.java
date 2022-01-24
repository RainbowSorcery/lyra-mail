package com.lyra.mail.ware.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.enums.WareContract;
import com.lyra.mail.ware.entity.WmsPurchase;
import com.lyra.mail.ware.entity.WmsPurchaseDetail;
import com.lyra.mail.ware.entity.vo.PurchaseDoneItemVO;
import com.lyra.mail.ware.entity.vo.PurchaseDoneVO;
import com.lyra.mail.ware.mapper.WmsPurchaseMapper;
import com.lyra.mail.ware.service.IWmsPurchaseDetailService;
import com.lyra.mail.ware.service.IWmsPurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.ware.service.IWmsWareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 采购信息 服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
@Service
public class WmsPurchaseServiceImpl extends ServiceImpl<WmsPurchaseMapper, WmsPurchase> implements IWmsPurchaseService {
    @Autowired
    private WmsPurchaseMapper purchaseMapper;

    @Autowired
    private IWmsPurchaseDetailService purchaseDetailService;

    @Autowired
    private IWmsWareSkuService wmsWareSkuService;

    @Override
    public IPage<WmsPurchase> findPurchaseList(Integer current, Integer pageSize, String key, Integer status) {
        QueryWrapper<WmsPurchase> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.eq("id",key).or();
            queryWrapper.like("assignee_name", key);
        }

        if (status != null) {
            queryWrapper.eq("status", status);
        }

        return purchaseMapper.selectPage(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public List<WmsPurchase> undeceiveList() {
        QueryWrapper<WmsPurchase> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", WareContract.PurchaseStatusEnum.CREATE.getCode()).or();
        queryWrapper.eq("status", WareContract.PurchaseStatusEnum.ASSIGNED.getCode());

        return purchaseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void received(List<Long> ids) {
        List<WmsPurchase> purchases = ids.stream().map((id) -> {
                    return purchaseMapper.selectById(id);
                }).filter((item) -> {
                    return Objects.equals(item.getStatus(), WareContract.PurchaseStatusEnum.CREATE.getCode()) ||
                            Objects.equals(item.getStatus(), WareContract.PurchaseStatusEnum.ASSIGNED.getCode());
                }).peek((item) -> {
                    item.setStatus(WareContract.PurchaseStatusEnum.PURCHASING.getCode());
                    item.setUpdateTime(LocalDateTime.now());
                })
                .collect(Collectors.toList());
        this.updateBatchById(purchases);

        purchases.forEach((purchase -> {
            List<WmsPurchaseDetail> purchaseDetails = purchaseDetailService.getPurchaseIdByPurchaseDetail(purchase.getId());

            List<WmsPurchaseDetail> detailList = purchaseDetails.stream().peek(
                    (purchaseDetail -> purchaseDetail.setStatus(WareContract.PurchaseDetailStatusEnum.PURCHASING.getCode()))
            ).collect(Collectors.toList());

            purchaseDetailService.updateBatchById(detailList);
        }));

    }

    @Override
    @Transactional
    public void done(PurchaseDoneVO purchaseDoneVO) {
        Long purchaseId = purchaseDoneVO.getId();

        List<PurchaseDoneItemVO> items = purchaseDoneVO.getItems();
        List<WmsPurchaseDetail> purchaseDetails = new ArrayList<>();

        boolean flag = true;
        for (PurchaseDoneItemVO item : items) {
            if (!Objects.equals(item.getStatus(), WareContract.PurchaseStatusEnum.DONE.getCode())) {
                flag = false;
                item.setStatus(WareContract.PurchaseStatusEnum.HAS_ERROR.getCode());
            } else {
                item.setStatus(WareContract.PurchaseStatusEnum.DONE.getCode());

                WmsPurchaseDetail detailServiceById = purchaseDetailService.getById(item.getItemId());

                // 进行入库操作
                wmsWareSkuService.addStock(detailServiceById.getSkuId(), detailServiceById.getWareId(),
                        detailServiceById.getSkuNum());
            }

            WmsPurchaseDetail purchaseDetail = new WmsPurchaseDetail();
            purchaseDetail.setId(item.getItemId());
            purchaseDetail.setStatus(item.getStatus());
            purchaseDetails.add(purchaseDetail);

        }

        purchaseDetailService.updateBatchById(purchaseDetails);

        WmsPurchase purchase = purchaseMapper.selectById(purchaseId);
        purchase.setId(purchaseId);
        purchase.setStatus(flag ? WareContract.PurchaseDetailStatusEnum.DONE.getCode() :
                WareContract.PurchaseDetailStatusEnum.HAS_ERROR.getCode());

        purchaseMapper.updateById(purchase);

    }
}
