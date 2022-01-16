package com.lyra.mail.product.feign;

import com.lyra.mail.common.result.Result;
import com.lyra.mail.common.to.BoundsTO;
import com.lyra.mail.common.to.SkuReductionTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "coupon-service")
public interface CouponFeignService {
    @PostMapping("/sms-spu-bounds/save")
    Result saveSpuBounds(@RequestBody  BoundsTO boundsTO);

    @PostMapping("/sms-sku-full-reduction/saveSkuFullReduction")
    Result skuReduction(@RequestBody SkuReductionTO skuReductionTO);
}
