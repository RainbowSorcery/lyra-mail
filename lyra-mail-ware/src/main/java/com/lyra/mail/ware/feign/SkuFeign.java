package com.lyra.mail.ware.feign;

import com.lyra.mail.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product-service", path = "/product/pms-sku-info")
public interface SkuFeign {
    @GetMapping("/info/{skuId}")
    public Result skuInfo(@PathVariable Long skuId);
}
