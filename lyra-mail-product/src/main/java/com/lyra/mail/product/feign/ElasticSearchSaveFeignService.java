package com.lyra.mail.product.feign;

import com.lyra.mail.common.result.Result;
import com.lyra.mail.common.to.SkuESEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "search-service")
@RequestMapping("/search/save")
public interface ElasticSearchSaveFeignService {
    @PostMapping("/product")
    public Result saveProduct(@RequestBody List<SkuESEntity> skuESEntities);
}
