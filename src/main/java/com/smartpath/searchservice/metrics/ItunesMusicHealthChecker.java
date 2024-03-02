package com.smartpath.searchservice.metrics;

import com.smartpath.searchservice.client.model.ItunesMusicResponse;
import com.smartpath.searchservice.client.service.ItunesMusicClient;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

//Todo: Security mitigation for exposed health endpoints (must be authorized by Admin)
@Component
@Endpoint(id="itunes")
public class ItunesMusicHealthChecker {

    private ItunesMusicClient itunesMusicClient;

    public ItunesMusicHealthChecker(ItunesMusicClient itunesMusicClient) {
        this.itunesMusicClient = itunesMusicClient;
    }

    @ReadOperation
    public Health health() {
        long startTime = System.currentTimeMillis();
        var itunesResponse = itunesMusicClient.getItunesMusics("RandomBooks"); // TODO Auto-generated method stub
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;
        return isSatisfied(itunesResponse, responseTime ) ?
                Health.up().withDetail("ItunesMusic ResponseTime", responseTime + "ms").build()
                : Health.down().withDetail("ItunesMusic ResponseTime", responseTime + "ms").build();
    }
    private Boolean isSatisfied(ItunesMusicResponse itunesMusicResponse, long responseTime){
        return itunesMusicResponse != null && responseTime <  25000; // Todo: responseTime should be more optimised

    }
}
