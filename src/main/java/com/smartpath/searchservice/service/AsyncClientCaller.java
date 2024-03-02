package com.smartpath.searchservice.service;

import com.smartpath.searchservice.client.service.GoogleBookClient;
import com.smartpath.searchservice.client.model.GoogleBooksResponse;
import com.smartpath.searchservice.client.service.ItunesMusicClient;
import com.smartpath.searchservice.client.model.ItunesMusicResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncClientCaller {
    private final ItunesMusicClient itunesMusicClient;
    private final GoogleBookClient googleBookClient;

    public AsyncClientCaller(ItunesMusicClient itunesMusicClient, GoogleBookClient googleBookClient) {
        this.itunesMusicClient = itunesMusicClient;
        this.googleBookClient = googleBookClient;
    }

    @Async("asyncExecutor")
    public CompletableFuture<ItunesMusicResponse> fetchItunesMusic(String input) {
        return CompletableFuture.completedFuture(itunesMusicClient.getItunesMusics(input));
    }
    @Async("asyncExecutor")
    public CompletableFuture<GoogleBooksResponse> fetchBooks(String input) {
        return CompletableFuture.completedFuture(googleBookClient.getGoogleBooks(input));
    }
}
