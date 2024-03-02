package com.smartpath.searchservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartpath.searchservice.web.dto.SearchResponse;

import java.util.concurrent.ExecutionException;

public interface MediaService {
    //Todo: using Java21 VirtualThread Feature is more readable, testable and maintainable
    SearchResponse getMediaBySearchString(String input) throws
            JsonProcessingException, InterruptedException, ExecutionException;
}
