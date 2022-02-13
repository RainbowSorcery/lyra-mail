package com.lyra.mail.search.controller;

import com.lyra.mail.common.result.ResponseStatusEnum;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.common.to.SkuESEntity;
import com.lyra.mail.search.contract.ElasticSearchContract;
import com.lyra.mail.search.service.IElasticSearchProductSaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search/save")
public class ElasticSearchSaveController {
    private Logger logger = LoggerFactory.getLogger(ElasticSearchContract.class);

    @Autowired
    private IElasticSearchProductSaveService elasticSearchProductSaveService;

    @PostMapping("/product")
    public Result saveProduct(@RequestBody List<SkuESEntity> skuESEntities) {

        Boolean result = true;
        try {
            result = elasticSearchProductSaveService.productUpToEs(skuESEntities);
        } catch (IOException e) {
            logger.error("上架失败{}", e.getMessage());
            return new Result(ResponseStatusEnum.PRODUCT_UP_ERROR.getCode(), ResponseStatusEnum.PRODUCT_UP_ERROR.getMessage(), false);
        }

        if (result) {
            return new Result(ResponseStatusEnum.PRODUCT_UP_ERROR.getCode(), ResponseStatusEnum.PRODUCT_UP_ERROR.getMessage(), false);
        } else {
            return Result.ok();
        }
    }
}
