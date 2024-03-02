package com.smartpath.searchservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "smart-path")
public class CustomAppConfig {

    public  Integer maxBooksNumber;
    public  Integer maxAlbumsNumber;

    public Integer getMaxBooksNumber() {
        return maxBooksNumber;
    }

    public void setMaxBooksNumber(Integer maxBooksNumber) {
        this.maxBooksNumber = maxBooksNumber;
    }

    public Integer getMaxAlbumsNumber() {
        return maxAlbumsNumber;
    }
    public void setMaxAlbumsNumber(Integer maxAlbumsNumber) {
        this.maxAlbumsNumber = maxAlbumsNumber;
    }
}
