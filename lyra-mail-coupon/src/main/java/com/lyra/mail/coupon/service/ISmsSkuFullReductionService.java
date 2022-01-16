package com.lyra.mail.coupon.service;

import com.lyra.mail.common.to.SkuReductionTO;
import com.lyra.mail.coupon.entity.SmsSkuFullReduction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品满减信息 服务类
 * </p>
 *
 * @author lyra
 * @since 2022-01-14
 */
public interface ISmsSkuFullReductionService extends IService<SmsSkuFullReduction> {

    void saveSkuFullReduction(SkuReductionTO skuReductionTO);

}
