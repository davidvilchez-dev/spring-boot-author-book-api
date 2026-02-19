package com.david.springboot.springboot_author_book_api.service;

import java.util.List;

import com.david.springboot.springboot_author_book_api.entity.Book;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    Book create(Book book, Long authorId);

    Book update(Long id, Book book);

    void delete(Long id);

}
