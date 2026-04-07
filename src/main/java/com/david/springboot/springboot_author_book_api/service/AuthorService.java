package com.david.springboot.springboot_author_book_api.service;

import java.util.List;

import com.david.springboot.springboot_author_book_api.dto.AuthorRequest;
import com.david.springboot.springboot_author_book_api.dto.AuthorResponse;
import com.david.springboot.springboot_author_book_api.dto.BookResponse;

public interface AuthorService {

    List<AuthorResponse> findAll();

    AuthorResponse findById(Long id);

    AuthorResponse create(AuthorRequest authorRequest);

    AuthorResponse update(Long id, AuthorRequest authorRequest);

    void delete(Long id);

    List<BookResponse> getBooksByAuthor(Long authorId);

}
