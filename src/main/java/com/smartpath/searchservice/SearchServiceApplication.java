package com.smartpath.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }

}
//Todo: resilience with circuit breaker pattern (resielence4J its a good library fro circuit breaker pattern)
//TODO:uint-tests
//Todo: Security --> specifically for metrics apis
