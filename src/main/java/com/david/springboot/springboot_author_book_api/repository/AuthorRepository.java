package com.david.springboot.springboot_author_book_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.springboot.springboot_author_book_api.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
