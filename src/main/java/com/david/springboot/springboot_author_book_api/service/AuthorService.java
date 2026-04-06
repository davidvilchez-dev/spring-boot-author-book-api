package com.david.springboot.springboot_author_book_api.service;

import java.util.List;

import com.david.springboot.springboot_author_book_api.entity.Author;
import com.david.springboot.springboot_author_book_api.entity.Book;

public interface AuthorService {

    List<Author> findAll();

    Author findById(Long id);

    Author create(Author author);

    Author update(Long id, Author author);

    void delete(Long id);

    List<Book> getBooksByAuthor(Long authorId);

}
