package com.lyra.mail.ware.mapper;

import com.lyra.mail.ware.entity.WmsWareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品库存 Mapper 接口
 * </p>
 *
 * @author lyra
 * @since 2022-01-16
 */
public interface WmsWareSkuMapper extends BaseMapper<WmsWareSku> {

    void insertStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    void updateSock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);
}
