package com.smartpath.searchservice.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpath.searchservice.client.model.ItunesMusicResponse;
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
@ConfigurationProperties(prefix = "smart-path.itunes", ignoreInvalidFields = true)
@Slf4j
public class ItunesMusicClient {

    public final String MUSIC_SEARCH_PATH = "/search?term=";
    private final RestTemplate restTemplate;
    private final ObjectMapper itunesObjectMapper;
    private String host;

    public ItunesMusicClient(RestTemplateBuilder restTemplateBuilder, ObjectMapper itunesObjectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.itunesObjectMapper = itunesObjectMapper;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ItunesMusicResponse getItunesMusics(String searchingText) {
        String apiUrl = host + MUSIC_SEARCH_PATH + searchingText;
        try {
            String textResponse = restTemplate.getForObject(apiUrl, String.class);
            ItunesMusicResponse response = itunesObjectMapper.readValue(textResponse, ItunesMusicResponse.class);
            return response;
        }catch (HttpClientErrorException | HttpServerErrorException | JsonProcessingException e){
            log.error("ItunesMusic Calling *** " +e.getMessage());
            //throw new DownstreamException("Error calling Itunes API");
            return new ItunesMusicResponse();
        }
    }
}

