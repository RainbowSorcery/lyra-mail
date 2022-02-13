package com.lyra.mail.search.service;

import com.lyra.mail.common.to.SkuESEntity;

import java.io.IOException;
import java.util.List;

public interface IElasticSearchProductSaveService {
    Boolean productUpToEs(List<SkuESEntity> skuESEntities) throws IOException;

}
