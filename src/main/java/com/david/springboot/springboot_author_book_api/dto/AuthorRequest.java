package com.david.springboot.springboot_author_book_api.dto;

import lombok.Data;

@Data
public class AuthorRequest {

    private String name;
    private String email;
    private String biography;
}
