package com.smartpath.searchservice.client.model;

import com.smartpath.searchservice.model.Music;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItunesMusicResponse {
    private List<Music> results;
}
