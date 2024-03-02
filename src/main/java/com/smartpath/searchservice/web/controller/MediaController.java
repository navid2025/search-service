package com.smartpath.searchservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartpath.searchservice.service.MediaService;
import com.smartpath.searchservice.web.dto.SearchResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Cacheable("cache")
    @GetMapping("/{input}")
    public SearchResponse getMediaBySearchString(@PathVariable String input) throws JsonProcessingException, ExecutionException, InterruptedException {
        return mediaService.getMediaBySearchString(input);
    }

}
