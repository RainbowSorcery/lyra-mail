package com.lyra.mail.ware.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.enums.WareContract;
import com.lyra.mail.ware.entity.WmsPurchase;
import com.lyra.mail.ware.entity.WmsPurchaseDetail;
import com.lyra.mail.ware.entity.vo.PurchaseMergeVO;
import com.lyra.mail.ware.mapper.WmsPurchaseDetailMapper;
import com.lyra.mail.ware.service.IWmsPurchaseDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyra.mail.ware.service.IWmsPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-22
 */
@Service
public class WmsPurchaseDetailServiceImpl extends ServiceImpl<WmsPurchaseDetailMapper, WmsPurchaseDetail> implements IWmsPurchaseDetailService {
    @Autowired
    private WmsPurchaseDetailMapper purchaseDetailMapper;

    @Autowired
    private IWmsPurchaseService purchaseService;

    @Override
    public IPage<WmsPurchaseDetail> findPurchaseList(Integer current, Integer pageSize, String key, Integer status, Long wareId) {
        QueryWrapper<WmsPurchaseDetail> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.eq("id", key).or();
        }

        if (status != null) {
            queryWrapper.eq("status", status);
        }

        if (wareId != null) {
            queryWrapper.eq("ware_id", wareId);
        }

        return purchaseDetailMapper.selectPage(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    @Transactional
    public void merge(PurchaseMergeVO mergeVO) {
        Long purchaseId = mergeVO.getPurchaseId();

        if (purchaseId == null) {
            WmsPurchase wmsPurchase = new WmsPurchase();
            wmsPurchase.setStatus(WareContract.PurchaseStatusEnum.ASSIGNED.getCode());
            wmsPurchase.setUpdateTime(LocalDateTime.now());
            wmsPurchase.setCreateTime(LocalDateTime.now());
            purchaseService.save(wmsPurchase);
            purchaseId = wmsPurchase.getId();
        }


        List<Long> items = mergeVO.getItems();

        Long finalPurchaseId = purchaseId;
        List<WmsPurchaseDetail> purchaseDetailList = items.stream().map((id) -> {
            WmsPurchaseDetail purchaseDetail = new WmsPurchaseDetail();
            purchaseDetail.setId(id);
            purchaseDetail.setPurchaseId(finalPurchaseId);
            purchaseDetail.setStatus(WareContract.PurchaseDetailStatusEnum.ASSIGNED.getCode());

            return purchaseDetail;
        }).filter((purchaseDetail -> {
            return Objects.equals(purchaseDetail.getStatus(), WareContract.PurchaseDetailStatusEnum.ASSIGNED.getCode()) ||
                    Objects.equals(purchaseDetail.getStatus(), WareContract.PurchaseStatusEnum.CREATE.getCode());
        })).collect(Collectors.toList());

        this.updateBatchById(purchaseDetailList);
        WmsPurchase wmsPurchase = new WmsPurchase();
        wmsPurchase.setId(purchaseId);
        wmsPurchase.setUpdateTime(LocalDateTime.now());
        purchaseService.updateById(wmsPurchase);

    }

    @Override
    public List<WmsPurchaseDetail> getPurchaseIdByPurchaseDetail(Long id) {
        QueryWrapper<WmsPurchaseDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("purchase_id", id);

        return purchaseDetailMapper.selectList(queryWrapper);
    }
}
