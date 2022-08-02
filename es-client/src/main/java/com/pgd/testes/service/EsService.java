package com.pgd.testes.service;

import com.pgd.testes.model.Hotel;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EsService {

    @Autowired
    RestHighLevelClient client;

    public List<Hotel> list(String keyword) {
        SearchRequest searchRequest = new SearchRequest("hotel");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("title", keyword));

        searchRequest.source(searchSourceBuilder);

        List<Hotel> hs = new ArrayList<>();
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus status = searchResponse.status();

            if(status != RestStatus.OK) {
                return null;
            }

            SearchHits searchHits = searchResponse.getHits();
            for(SearchHit searchHit : searchHits) {
                Map<String, Object> map = searchHit.getSourceAsMap();

                Hotel h = new Hotel();
                h.setId(searchHit.getId());
                h.setIndex(searchHit.getIndex());
                h.setScore(searchHit.getScore());
                h.setTitle(map.get("title").toString());
                h.setCity(map.get("city").toString());
                h.setPrice((Double)map.get("price"));
                hs.add(h);
            }
            return hs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hs;
    }
}





















