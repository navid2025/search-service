package com.smartpath.searchservice.service;

import com.smartpath.searchservice.client.model.GoogleBooksResponse;
import com.smartpath.searchservice.client.model.ItunesMusicResponse;
import com.smartpath.searchservice.config.CustomAppConfig;
import com.smartpath.searchservice.web.dto.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Primary
@Slf4j
public class MediaAsyncService implements MediaService {
    private final AsyncClientCaller asyncClientCaller;
    private final CustomAppConfig customAppConfig;

    public MediaAsyncService(AsyncClientCaller asyncClientCaller, CustomAppConfig customAppConfig) {
        this.asyncClientCaller = asyncClientCaller;
        this.customAppConfig = customAppConfig;
    }



    //Todo: using Java21 VirtualThread Feature is more readable ,debugable, and testable
    @Override
    public SearchResponse getMediaBySearchString(String input) throws
             InterruptedException, ExecutionException {

        CompletableFuture<ItunesMusicResponse> itunesResponseFuture = asyncClientCaller.fetchItunesMusic(input);
        CompletableFuture<GoogleBooksResponse> googleResponseFuture = asyncClientCaller.fetchBooks(input);
        return CompletableFuture
                .allOf(itunesResponseFuture, googleResponseFuture)
                .thenApply(ignored -> {
                    try {
                        ItunesMusicResponse itunesMusicResponse = itunesResponseFuture.get();
                        GoogleBooksResponse googleBooksResponse = googleResponseFuture.get();
                        SearchResponse searchResponse = new SearchResponse();
                        if (googleBooksResponse != null)
                            searchResponse.setBooks(googleBooksResponse.getItems()
                                    .stream().limit(customAppConfig.getMaxBooksNumber()).collect(Collectors.toList()));
                        if (itunesMusicResponse != null)
                            searchResponse.setMusics(itunesMusicResponse.getResults()
                                    .stream().limit(customAppConfig.getMaxAlbumsNumber()).collect(Collectors.toList()));
                        searchResponse.setResultCode("0");
                        searchResponse.setResultDesc("success");
                        return searchResponse;


                    }catch (ExecutionException  | InterruptedException exception){
                        log.error("Execution Exception: " + exception.getMessage());
                        throw new RuntimeException(exception); //Todo: Customized Exception
                    }
                })
                .get();
    }
}
