package com.david.springboot.springboot_author_book_api.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "name", "email", "biography" })
public class AuthorResponse {
    private Long id;

    private String name;
    private String email;
    private String biography;
}
