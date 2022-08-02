package com.pgd.testes.service;

import com.pgd.testes.model.Hotel;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class EsService {

    @Autowired
    JestClient jestClient;

    public List<Hotel> list(String title) {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", title);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(matchQueryBuilder);

        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("hotel").build();

        try {
            SearchResult jestResult = jestClient.execute(search);

            if(jestResult.isSucceeded()) {
                return jestResult.getSourceAsObjectList(Hotel.class, false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

































}
