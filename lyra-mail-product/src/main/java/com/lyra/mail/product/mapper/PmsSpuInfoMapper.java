package com.lyra.mail.product.mapper;

import com.lyra.mail.common.enums.ProductConstruct;
import com.lyra.mail.product.entity.PmsSpuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * spu信息 Mapper 接口
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface PmsSpuInfoMapper extends BaseMapper<PmsSpuInfo> {

    void changeSpuStatusUp(@Param("spuId") Long spuId, @Param("upStatus") Integer upStatus);
}
