package com.lyra.mail.product.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.to.BoundsTO;
import com.lyra.mail.common.to.SkuReductionTO;
import com.lyra.mail.product.entity.*;
import com.lyra.mail.product.entity.vo.*;
import com.lyra.mail.product.feign.CouponFeignService;
import com.lyra.mail.product.mapper.PmsSpuInfoMapper;
import com.lyra.mail.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * spu信息 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsSpuInfoServiceImpl extends ServiceImpl<PmsSpuInfoMapper, PmsSpuInfo> implements IPmsSpuInfoService {
    @Autowired
    private PmsSpuInfoMapper spuInfoMapper;

    @Autowired
    private IPmsSpuImagesService spuImagesService;

    @Autowired
    private IPmsSpuInfoDescService spuInfoDescService;

    @Autowired
    private IPmsAttrService attrService;

    @Autowired
    private IPmsProductAttrValueService pmsProductAttrValueService;

    @Autowired
    private IPmsSkuInfoService skuInfoService;

    @Autowired
    private IPmsSkuImagesService skuImagesService;

    @Autowired
    private IPmsSkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponFeignService couponFeignService;

    @Override
    @Transactional
    public void saveSpuInfo(SpuVO spuVO) {
        // 保存spu info
        PmsSpuInfo spuInfo = new PmsSpuInfo();
        BeanUtils.copyProperties(spuVO, spuInfo);
        spuInfo.setCreateTime(LocalDateTime.now());
        spuInfo.setUpdateTime(LocalDateTime.now());
        this.saveSpuInfo(spuInfo);
        Long spuId = spuInfo.getId();

        // 保存图片信息
        List<String> spuImages = spuVO.getImages();
        List<PmsSpuImages> spuImageList = spuImages.stream().map((img) -> {
            PmsSpuImages pmsSpuImages = new PmsSpuImages();
            pmsSpuImages.setSpuId(spuId);
            String fileName = img.substring(img.lastIndexOf("/") + 1);
            pmsSpuImages.setImgName(fileName);
            pmsSpuImages.setImgUrl(img);
            return pmsSpuImages;
        }).collect(Collectors.toList());
        spuImagesService.saveBatch(spuImageList);

        // 保存spu介绍信息
        List<String> decriptStringList = spuVO.getDecript();
        PmsSpuInfoDesc spuInfoDesc = new PmsSpuInfoDesc();
        spuInfoDesc.setDecript(String.join(";", decriptStringList));
        spuInfoDescService.save(spuInfoDesc);

        // 保存spu属性值
        List<BaseAttrVO> baseAttrs = spuVO.getBaseAttrs();
        List<PmsProductAttrValue> productAttrValues = baseAttrs.stream().map((baseAttrVO -> {
            PmsProductAttrValue pmsProductAttrValue = new PmsProductAttrValue();
            pmsProductAttrValue.setSpuId(spuId);
            pmsProductAttrValue.setAttrId(baseAttrVO.getAttrId());
            PmsAttr attr = attrService.getById(baseAttrVO.getAttrId());
            pmsProductAttrValue.setAttrName(attr.getAttrName());
            pmsProductAttrValue.setAttrValue(baseAttrVO.getAttrValues());
            pmsProductAttrValue.setQuickShow(baseAttrVO.getShowDesc());

            return pmsProductAttrValue;
        })).collect(Collectors.toList());
        pmsProductAttrValueService.saveBatch(productAttrValues);

        // 保存积分信息
        BoundVO bounds = spuVO.getBounds();
        BoundsTO boundsTO = new BoundsTO();
        BeanUtils.copyProperties(bounds, boundsTO);
        boundsTO.setSpuId(spuId);
        couponFeignService.saveSpuBounds(boundsTO);

        // 保存SKU
        List<SkuVO> skus = spuVO.getSkus();

        skus.forEach((sku) -> {
            // 保存skuInfo
            String defaultImage = "";
            List<ImageVO> images = sku.getImages();
            List<ImageVO> skuImages = images;

            for (ImageVO skuImage : skuImages) {
                if (skuImage.getDefaultImg() == 1) {
                    defaultImage = skuImage.getImgUrl();
                }
            }

            PmsSkuInfo skuInfo = new PmsSkuInfo();
            BeanUtils.copyProperties(sku, skuInfo);
            skuInfo.setSpuId(spuId);
            skuInfo.setCatalogId(spuInfo.getCatalogId());
            skuInfo.setBrandId(spuInfo.getBrandId());
            skuInfo.setSkuDefaultImg(defaultImage);
            skuInfo.setSaleCount(0L);
            skuInfoService.save(skuInfo);

            // 保存sku图片
            List<PmsSkuImages> pmsSkuImageList = images.stream().map((skuImage) -> {
                PmsSkuImages pmsSkuImages = new PmsSkuImages();
                pmsSkuImages.setImgUrl(skuImage.getImgUrl());
                pmsSkuImages.setSkuId(skuInfo.getSkuId());
                pmsSkuImages.setDefaultImg(skuImage.getDefaultImg());

                return pmsSkuImages;
            }).collect(Collectors.toList());
            skuImagesService.saveBatch(pmsSkuImageList);

            // 保存sku销售属性
            List<AttrVO> skuVoAttrs = sku.getAttr();
            List<PmsSkuSaleAttrValue> skuSaleAttrValues = skuVoAttrs.stream().map((skuVoAttr) -> {
                PmsSkuSaleAttrValue skuSaleAttrValue = new PmsSkuSaleAttrValue();
                BeanUtils.copyProperties(skuVoAttr, skuSaleAttrValue);
                skuSaleAttrValue.setSkuId(skuInfo.getSkuId());

                return skuSaleAttrValue;
            }).collect(Collectors.toList());
            skuSaleAttrValueService.saveBatch(skuSaleAttrValues);

            // 保存优惠信息
            SkuReductionTO skuReductionTO = new SkuReductionTO();
            BeanUtils.copyProperties(sku, skuReductionTO);
            List<MemberPrice> memberPrice = sku.getMemberPrice();
            List<com.lyra.mail.common.to.MemberPrice> memberPrices = memberPrice.stream().map((skuMemberPrice) -> {
                com.lyra.mail.common.to.MemberPrice memberPrice1 = new com.lyra.mail.common.to.MemberPrice();
                BeanUtils.copyProperties(skuMemberPrice, memberPrice1);

                return memberPrice1;
            }).collect(Collectors.toList());

            skuReductionTO.setMemberPrice(memberPrices);

            skuReductionTO.setSkuId(skuInfo.getSkuId());

            couponFeignService.skuReduction(skuReductionTO);
        });
    }

    @Override
    public void saveSpuInfo(PmsSpuInfo spuInfo) {
        spuInfoMapper.insert(spuInfo);
    }

    @Override
    public Page<PmsSpuInfo> SpuInfoListPage(Integer current, Integer pageSize, String key, Long catelogId, Long brandId, Integer status) {
        QueryWrapper<PmsSpuInfo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(key)) {
            queryWrapper.eq("id", key).or().like("spu_name", key);
        }

        if (catelogId != null && catelogId != 0) {
            queryWrapper.eq("catalog_id", catelogId);
        }

        if (brandId != null && brandId != 0) {
            queryWrapper.eq("brand_id", brandId);
        }

        if (status != null) {
            queryWrapper.eq("publish_status", status);
        }

        return spuInfoMapper.selectPage(new Page<>(current, pageSize), queryWrapper);
    }
}
