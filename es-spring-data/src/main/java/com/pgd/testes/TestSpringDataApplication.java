package com.pgd.testes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
public class TestSpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringDataApplication.class, args);
	}

}
