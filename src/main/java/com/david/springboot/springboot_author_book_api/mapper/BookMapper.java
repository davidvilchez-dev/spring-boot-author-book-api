package com.david.springboot.springboot_author_book_api.mapper;

import com.david.springboot.springboot_author_book_api.dto.AuthorBasicResponse;
import com.david.springboot.springboot_author_book_api.dto.BookResponse;
import com.david.springboot.springboot_author_book_api.entity.Book;

public class BookMapper {

    public static BookResponse toBookResponse(Book book) {

        AuthorBasicResponse author = new AuthorBasicResponse();

        author.setId(book.getAuthor().getId());
        author.setName(book.getAuthor().getName());

        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setGender(book.getGender());
        bookResponse.setPublished(book.getPublished());
        bookResponse.setAuthor(author);
        return bookResponse;

    }

}
