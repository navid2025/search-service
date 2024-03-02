package com.smartpath.searchservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Book {

    private String title;
    private String subtitle;
    private List<String> authors;
    private String publishedDate;
    private String publisher;
    private String description;
    private String pageCount;
    private List<String> categories;
}
