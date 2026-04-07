package com.david.springboot.springboot_author_book_api.mapper;

import com.david.springboot.springboot_author_book_api.dto.AuthorRequest;
import com.david.springboot.springboot_author_book_api.dto.AuthorResponse;
import com.david.springboot.springboot_author_book_api.entity.Author;

public class AuthorMapper {

    public static AuthorResponse toAuthorResponse(Author author) {
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setBiography(author.getBiography());
        authorResponse.setEmail(author.getEmail());
        return authorResponse;
    }

    public static Author toAuthorEntity(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setBiography(authorRequest.getBiography());
        author.setEmail(authorRequest.getEmail());
        return author;
    }

}
