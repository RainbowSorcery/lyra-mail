package com.lyra.mail.ware.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyra.mail.common.result.ResponseStatusEnum;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.common.to.SkuInfoTO;
import com.lyra.mail.common.to.WareSkuHasStockTO;
import com.lyra.mail.ware.entity.WmsWareSku;
import com.lyra.mail.ware.feign.SkuFeign;
import com.lyra.mail.ware.mapper.WmsWareSkuMapper;
import com.lyra.mail.ware.service.IWmsWareSkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品库存 服务实现类
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
@Service
public class WmsWareSkuServiceImpl extends ServiceImpl<WmsWareSkuMapper, WmsWareSku> implements IWmsWareSkuService {
    @Autowired
    private WmsWareSkuMapper wmsWareSkuMapper;

    @Autowired
    private SkuFeign skuFeign;

    @Override
    public IPage<WmsWareSku> findWareSkuList(Integer current, Integer pageSize, Long skuId, Long wareId) {
        QueryWrapper<WmsWareSku> queryWrapper = new QueryWrapper<>();

        if (skuId != null) {
            queryWrapper.eq("sku_id", skuId);
        }

        if (wareId != null) {
            queryWrapper.eq("ware_id", wareId);
        }

        return wmsWareSkuMapper.selectPage(new Page<>(current, pageSize), queryWrapper);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        QueryWrapper<WmsWareSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id", skuId);
        queryWrapper.eq("ware_id", wareId);
        List<WmsWareSku> wmsWareSkus = wmsWareSkuMapper.selectList(queryWrapper);

        if (wmsWareSkus == null || wmsWareSkus.size() == 0) {
            Result result = skuFeign.skuInfo(skuId);
            if (!Objects.equals(result.getStatusCode(), ResponseStatusEnum.SUCCESS.getCode())) {
                throw new RuntimeException("请求错误");
            }
            Map<String, Objects> dataMap = (Map<String, Objects>) result.getData();
            WmsWareSku wareSku = new WmsWareSku();

            wareSku.setSkuId(skuId);
            wareSku.setWareId(wareId);
            wareSku.setStock(skuNum);
            wareSku.setSkuName(String.valueOf(dataMap.get("skuName")));

            wmsWareSkuMapper.insert(wareSku);
        } else {
            wmsWareSkuMapper.updateSock(skuId, wareId, skuNum);
        }
    }

    @Override
    public List<WareSkuHasStockTO> skuIdsHasStock(List<Long> skuIds) {
        return skuIds.stream().map((skuId) -> {
            WareSkuHasStockTO wareSkuHasStockTO = new WareSkuHasStockTO();
            Long skuCount = wmsWareSkuMapper.getSkuStockCount(skuId);
            wareSkuHasStockTO.setSkuId(skuId);
            // 如果搜不到代表没库存
            if (skuCount == null) {
                wareSkuHasStockTO.setHasStock(false);
            } else {
                wareSkuHasStockTO.setHasStock(skuCount > 0);
            }

            return wareSkuHasStockTO;
        }).collect(Collectors.toList());
    }
}
