package com.lyra.mail.product.feign;

import com.lyra.mail.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "ware-service")
@RequestMapping("/wms-ware-sku")
public interface WareFeignService {
    @PostMapping("/hasStock")
    public Result hasStock(@RequestBody List<Long> skuIds);
}
