package com.lyra.mail.product.mapper;

import com.lyra.mail.product.entity.PmsSkuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyra.mail.product.entity.vo.ItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * sku销售属性&值 Mapper 接口
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface PmsSkuSaleAttrValueMapper extends BaseMapper<PmsSkuSaleAttrValue> {

    List<ItemVO.skuItemSaleAttrVO> getSaleAttrsBySpuId(@Param("spuId") Long spuId);
}
