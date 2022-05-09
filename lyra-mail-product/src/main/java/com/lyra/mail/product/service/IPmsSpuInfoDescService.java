package com.lyra.mail.product.service;

import com.lyra.mail.product.entity.PmsSpuInfoDesc;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * spu信息介绍 服务类
 * </p>
 *
 * @author BackgroundPony
 * @since 2021-10-24
 */
public interface IPmsSpuInfoDescService extends IService<PmsSpuInfoDesc> {

    PmsSpuInfoDesc getDesc(Long spuId);
}
