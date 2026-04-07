package com.david.springboot.springboot_author_book_api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "title", "gender", "published", "author" })
public class BookResponse {

    private Long id;
    private String title;
    private String gender;
    private Date published;
    private AuthorBasicResponse author;

}
