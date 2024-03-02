package com.smartpath.searchservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Music {

    private String kind;
    private Long artistId;
    private Long trackId;
    private String artistName;
    private String collectionName;
    private String trackName;
    private String collectionCensoredName;
    private String trackCensoredName;
    private String releaseDate;
}
