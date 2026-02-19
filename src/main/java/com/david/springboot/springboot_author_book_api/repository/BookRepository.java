package com.david.springboot.springboot_author_book_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.springboot.springboot_author_book_api.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
