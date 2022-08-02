package com.pgd.testes.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "hotel")
@Data
public class Hotel {
    @Id
    String id;
    String title;
    String city;
    Double price;
}
