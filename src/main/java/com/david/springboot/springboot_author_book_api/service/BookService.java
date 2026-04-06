package com.david.springboot.springboot_author_book_api.service;

import java.util.List;

import com.david.springboot.springboot_author_book_api.dto.BookRequest;
import com.david.springboot.springboot_author_book_api.dto.BookResponse;

public interface BookService {

    List<BookResponse> findAll();

    BookResponse findById(Long id);

    BookResponse create(BookRequest bookRequest);

    BookResponse update(Long id, BookRequest bookRequest);

    void delete(Long id);

}
