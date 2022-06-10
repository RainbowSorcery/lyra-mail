package com.lyra.mail.product.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.product.entity.PmsSkuImages;
import com.lyra.mail.product.entity.PmsSkuInfo;
import com.lyra.mail.product.entity.PmsSpuImages;
import com.lyra.mail.product.entity.PmsSpuInfoDesc;
import com.lyra.mail.product.entity.vo.ItemVO;
import com.lyra.mail.product.mapper.PmsSkuInfoMapper;
import com.lyra.mail.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsSkuInfoServiceImpl extends ServiceImpl<PmsSkuInfoMapper, PmsSkuInfo> implements IPmsSkuInfoService {
    @Autowired
    private PmsSkuInfoMapper skuInfoMapper;

    @Autowired
    private IPmsSkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private IPmsSkuImagesService skuImagesService;

    @Autowired
    private IPmsAttrGroupService attrGroupService;

    @Autowired
    private IPmsSpuInfoDescService spuInfoDescService;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public IPage<PmsSkuInfo> skuPageList(Integer current, Integer pageSize, Long catelogId, Long brandId, Integer min, Integer max, String key) {
        QueryWrapper<PmsSkuInfo> queryWrapper = new QueryWrapper<>();

        if (catelogId != null && catelogId != 0) {
            queryWrapper.eq("catalog_id", catelogId);
        }

        if (brandId != null && brandId != 0) {
            queryWrapper.eq("brand_id", brandId);
        }

        if (min != null && min >= 0) {
            queryWrapper.ge("price", min);
        }

        if (max != null && max > 0) {
            queryWrapper.le("price", max);
        }

        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.like("sku_name", key).or();
            queryWrapper.eq("id", key);
        }

        return skuInfoMapper.selectPage(new Page<>(current, pageSize), queryWrapper);

    }

    @Override
    public ItemVO item(Long skuId) {
        ItemVO itemVO = new ItemVO();

        CompletableFuture<PmsSkuInfo> pmsSkuInfoCompletableFuture = CompletableFuture.supplyAsync(() -> {
            PmsSkuInfo pmsSkuInfo = skuInfoMapper.selectById(skuId);
            itemVO.setSkuInfo(pmsSkuInfo);

            return pmsSkuInfo;
        }, threadPoolExecutor);

        CompletableFuture<Void> spuInfoDescCompletableFuture = pmsSkuInfoCompletableFuture.thenAcceptAsync((result) -> {
            PmsSpuInfoDesc spuInfoDesc = spuInfoDescService.getDesc(result.getSpuId());
            itemVO.setSpuInfoDesc(spuInfoDesc);
        }, threadPoolExecutor);

        CompletableFuture<Void> spuItemAttrCompletableFuture = pmsSkuInfoCompletableFuture.thenAcceptAsync((result) -> {
            List<ItemVO.SpuItemAttrVO> spuItemAttrVOS = attrGroupService.getAttrGroupWithAttrsBySpuId(result.getSpuId(), result.getCatalogId());
            itemVO.setSpuItemAttrs(spuItemAttrVOS);
        }, threadPoolExecutor);


        CompletableFuture<Void> skuImagesCompletableFuture = CompletableFuture.runAsync(() -> {
            List<PmsSkuImages> skuImages = skuImagesService.getSkuImagesBySkuId(skuId);
            itemVO.setSkuImages(skuImages);
        }, threadPoolExecutor);

        CompletableFuture<Void> saleAttrCompletableFuture = pmsSkuInfoCompletableFuture.thenAcceptAsync((result) -> {
            List<ItemVO.skuItemSaleAttrVO> skuItemSaleAttrVOS = skuSaleAttrValueService.getSaleAttrsBySpuId(result.getSpuId());
            itemVO.setSaleAttr(skuItemSaleAttrVOS);
        }, threadPoolExecutor);


        CompletableFuture.allOf(pmsSkuInfoCompletableFuture,
                spuInfoDescCompletableFuture,
                spuItemAttrCompletableFuture,
                skuImagesCompletableFuture,
                saleAttrCompletableFuture);


        return itemVO;
    }


}
