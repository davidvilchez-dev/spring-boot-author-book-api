package com.david.springboot.springboot_author_book_api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BookResponse {

    private Long id;
    private String title;
    private String gender;
    private Date published;

}
