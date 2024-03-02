package com.smartpath.searchservice.web.dto;

import com.smartpath.searchservice.model.Book;
import com.smartpath.searchservice.model.Music;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse extends BaseResponse{
    private List<Music> musics;
    private List<Book> books;

}
