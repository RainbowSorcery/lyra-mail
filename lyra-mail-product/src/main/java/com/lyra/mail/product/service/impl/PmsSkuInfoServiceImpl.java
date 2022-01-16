package com.lyra.mail.product.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.product.entity.PmsSkuInfo;
import com.lyra.mail.product.mapper.PmsSkuInfoMapper;
import com.lyra.mail.product.service.IPmsSkuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
