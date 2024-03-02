package com.smartpath.searchservice.metrics;

import com.smartpath.searchservice.client.model.GoogleBooksResponse;
import com.smartpath.searchservice.client.service.GoogleBookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

//Todo: Security mitigation for exposed health endpoints (must be authorized by Admin)
@Component
@Endpoint(id="google")
public class GoogleBookHealthChecker {

    private GoogleBookClient googleBookClient;

    @Autowired
    public void setGoogleBookClient(GoogleBookClient googleBookClient) {
        this.googleBookClient = googleBookClient;
    }


    @ReadOperation
    public Health health() {
        long startTime = System.currentTimeMillis();
        var googleResponse = googleBookClient.getGoogleBooks("RandomBooks"); // TODO Auto-generated method stub

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;
        return isSatisfied(googleResponse, responseTime ) ?
                Health.up().withDetail("GoogleBook ResponseTime", responseTime + "ms").build()
                : Health.down().withDetail("GoogleBook ResponseTime", responseTime + "ms").build();
    }
    private Boolean isSatisfied(GoogleBooksResponse googleResponse, long responseTime){
        return googleResponse != null && responseTime <  25000; // Todo: responseTime should be more optimised

    }
}
