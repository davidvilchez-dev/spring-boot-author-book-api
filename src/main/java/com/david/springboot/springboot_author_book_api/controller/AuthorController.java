package com.david.springboot.springboot_author_book_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.springboot.springboot_author_book_api.dto.AuthorRequest;
import com.david.springboot.springboot_author_book_api.dto.AuthorResponse;
import com.david.springboot.springboot_author_book_api.dto.BookResponse;
import com.david.springboot.springboot_author_book_api.entity.Author;
import com.david.springboot.springboot_author_book_api.entity.Book;
import com.david.springboot.springboot_author_book_api.service.AuthorService;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {

        List<AuthorResponse> authors = authorService.findAll();

        return ResponseEntity.ok(authors);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {

        AuthorResponse author = authorService.findById(id);

        return ResponseEntity.ok(author);

    }

    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody AuthorRequest author) {

        AuthorResponse authorCreated = authorService.create(author);

        return ResponseEntity.status(HttpStatus.CREATED).body(authorCreated);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest author) {

        AuthorResponse authorUpdated = authorService.update(id, author);

        return ResponseEntity.ok(authorUpdated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

        authorService.delete(id);
        return ResponseEntity.noContent().build();

    }

    // ============= Endpoint para manejar la relación con Books =============

    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<BookResponse>> getBooksByAuthor(@PathVariable Long authorId) {

        List<BookResponse> books = authorService.getBooksByAuthor(authorId);
        return ResponseEntity.ok(books);
    }

}
