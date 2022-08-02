package com.pgd.testes.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class EsClient {
    @Value("${elasticsearch.rest.hosts}")
    private String hosts;

    @Value("${elasticsearch.rest.username}")
    private String username;

    @Value("${elasticsearch.rest.password}")
    private String password;

    @Bean
    public RestHighLevelClient initSimpleClient() {
        HttpHost[] httpHosts = Arrays.stream(hosts.split(",")).map(hotst -> {
                    String[] hostParts = hotst.split(":");
                   return new HttpHost(hostParts[0], Integer.valueOf(hostParts[1]));
                }).filter(Objects::nonNull).toArray(HttpHost[]::new);

//        CredentialsProvider provider = new BasicCredentialsProvider();
//        provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

//        .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(provider))

        return new RestHighLevelClient(RestClient.builder(httpHosts));
    }
}
