package com.smartpath.searchservice.client.service;

import com.smartpath.searchservice.client.model.GoogleBooksResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "smart-path.google", ignoreInvalidFields = true)
@Slf4j
public class GoogleBookClient {

    private final RestTemplate restTemplate;

    public final String BOOK_SEARCH_PATH = "/books/v1/volumes?q=";

    private String host;

    public GoogleBookClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public GoogleBooksResponse getGoogleBooks(String searchingText) {

        String apiUrl = host + BOOK_SEARCH_PATH + searchingText;
        try {
            return restTemplate.getForObject(apiUrl, GoogleBooksResponse.class);
        }catch(HttpClientErrorException | HttpServerErrorException e){
            log.error("GoogleBooks Calling *** " + e.getMessage());
            //throw new DownstreamException("Error calling Google API");
            return null;
        }
    }
}
