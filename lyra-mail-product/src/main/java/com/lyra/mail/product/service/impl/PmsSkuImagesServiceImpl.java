package com.lyra.mail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyra.mail.product.entity.PmsSkuImages;
import com.lyra.mail.product.mapper.PmsSkuImagesMapper;
import com.lyra.mail.product.service.IPmsSkuImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * sku图片 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsSkuImagesServiceImpl extends ServiceImpl<PmsSkuImagesMapper, PmsSkuImages> implements IPmsSkuImagesService {
    @Autowired
    private PmsSkuImagesMapper skuImagesMapper;

    @Override
    public List<PmsSkuImages> getSkuImagesBySkuId(Long skuId) {
        return
                skuImagesMapper.selectList(new QueryWrapper<PmsSkuImages>().eq("sku_id", skuId));
    }
}
