package com.lyra.mail.search.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperationVariant;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponseItem;
import com.lyra.mail.common.result.Result;
import com.lyra.mail.search.entity.Bank;
import com.lyra.mail.search.entity.MyIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/es")
public class EsController {
    @Autowired
    private ElasticsearchClient elasticsearchClient;


    @GetMapping("/search")
    public Result search() {
        MyIndex myIndex = new MyIndex();
        myIndex.setName("bon");
        myIndex.setAge(70);
        myIndex.setEmail("123@qq.com");
        List<BulkOperation> bulkOperations = new ArrayList<>();
        BulkOperation bulkOperation = new BulkOperation.Builder()
                .create((doc) -> doc.document(myIndex).id("1245689").index("my-index-00003"))
                .build();
        BulkOperation bulkOperation1 = new BulkOperation.Builder()
                .create((doc) -> doc.document(myIndex).id("123777777").index("my-index-00003"))
                .build();
        bulkOperations.add(bulkOperation);
        bulkOperations.add(bulkOperation1);

        BulkRequest bulkRequest = new BulkRequest.Builder().index("my-index-00003")
                .operations(bulkOperations).build();

        try {
            BulkResponse bulk = elasticsearchClient.bulk(bulkRequest);
            System.out.println(bulk.errors());
            List<BulkResponseItem> items = bulk.items();
            System.out.println(bulk.items());
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        Map<String, Aggregation> aggs = new HashMap<>();
//        aggs.put("bankTerm", Aggregation.of((c) -> {
//            return c.terms(TermsAggregation.of((t) -> {
//                return t.field("age");
//            })).aggregations("ageAvg", Aggregation.of((t) -> {
//                return t.avg((v) -> {
//                    return v.field("balance");
//                });
//            }));
//        }));
//
//        SearchResponse<Bank> bankSearchResponse = null;
//        try {
//            bankSearchResponse = elasticsearchClient.search((e) -> {
//                return e.index("bank")
//                        .query((q) -> {
//                            return q.matchAll(new MatchAllQuery.Builder().build());
//                        })
//                        .aggregations(aggs);
//            }, Bank.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Aggregate bankTerm = bankSearchResponse.aggregations().get("bankTerm");
//        LongTermsAggregate lterms = bankTerm.lterms();
//        Buckets<LongTermsBucket> buckets = lterms.buckets();
//
//        List<LongTermsBucket> array = buckets.array();
//        List<Map<String, Object>> bucketList = new ArrayList<>();
//
//        for (LongTermsBucket longTermsBucket : array) {
//            Map<String, Object> bucket = new HashMap<>();
//            bucket.put("key", longTermsBucket.key());
//            bucket.put("docCount", longTermsBucket.docCount());
//
//            Map<String, Aggregate> aggregations = longTermsBucket.aggregations();
//            Aggregate ageAvg = aggregations.get("ageAvg");
//            double value = ageAvg.avg().value();
//            bucket.put("avgValue", value);
//
//            bucketList.add(bucket);
//        }

        return Result.ok(null);
    }
}
