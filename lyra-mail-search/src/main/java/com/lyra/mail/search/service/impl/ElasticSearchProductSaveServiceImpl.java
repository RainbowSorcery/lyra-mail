package com.lyra.mail.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.lyra.mail.common.to.SkuESEntity;
import com.lyra.mail.search.contract.ElasticSearchContract;
import com.lyra.mail.search.service.IElasticSearchProductSaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElasticSearchProductSaveServiceImpl implements IElasticSearchProductSaveService {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private Logger logger = LoggerFactory.getLogger(ElasticSearchProductSaveServiceImpl.class);

    @Override
    public Boolean productUpToEs(List<SkuESEntity> skuESEntities) throws IOException {
        List<BulkOperation> bulkOperations = new ArrayList<>();

        skuESEntities.forEach((sku) -> {
            BulkOperation bulkOperation = new BulkOperation.Builder().create((doc) -> doc.index(ElasticSearchContract.PRODUCT_INDEX)
                    .document(sku)
                    .id(sku.getSkuId().toString())).build();
            bulkOperations.add(bulkOperation);
        });

        BulkRequest bulkRequest = new BulkRequest.Builder()
                .index(ElasticSearchContract.PRODUCT_INDEX).operations(bulkOperations).build();
        BulkResponse bulkResponse = elasticsearchClient.bulk(bulkRequest);

        List<BulkResponseItem> items = bulkResponse.items();
        List<BulkResponseItem> upErrorSkuList = items.stream().filter((item) -> {
            return item.error() != null;
        }).collect(Collectors.toList());

        if (upErrorSkuList.size() > 0) {
            logger.error("上架失败{}", upErrorSkuList);
        }

        return bulkResponse.errors();
    }
}
