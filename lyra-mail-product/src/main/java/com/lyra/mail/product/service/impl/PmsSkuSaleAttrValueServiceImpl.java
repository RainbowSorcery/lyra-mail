package com.lyra.mail.product.service.impl;

import com.lyra.mail.product.entity.PmsSkuSaleAttrValue;
import com.lyra.mail.product.entity.vo.ItemVO;
import com.lyra.mail.product.mapper.PmsSkuSaleAttrValueMapper;
import com.lyra.mail.product.service.IPmsSkuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * sku销售属性&值 服务实现类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
@Service
public class PmsSkuSaleAttrValueServiceImpl extends ServiceImpl<PmsSkuSaleAttrValueMapper, PmsSkuSaleAttrValue> implements IPmsSkuSaleAttrValueService {
    @Autowired
    private PmsSkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public List<ItemVO.skuItemSaleAttrVO> getSaleAttrsBySpuId(Long spuId) {

        return skuSaleAttrValueMapper.getSaleAttrsBySpuId(spuId);
    }
}
