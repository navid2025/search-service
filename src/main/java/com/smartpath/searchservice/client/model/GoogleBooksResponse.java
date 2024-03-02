package com.smartpath.searchservice.client.model;

import com.smartpath.searchservice.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class GoogleBooksResponse {
    private List<Book> items;
}
