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

import com.david.springboot.springboot_author_book_api.entity.Author;
import com.david.springboot.springboot_author_book_api.entity.Book;
import com.david.springboot.springboot_author_book_api.service.AuthorService;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {

        List<Author> authors = authorService.findAll();

        return ResponseEntity.ok(authors);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {

        Author authorOptional = authorService.findById(id);

        return ResponseEntity.ok(authorOptional);

    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {

        Author authorCreated = authorService.create(author);

        return ResponseEntity.status(HttpStatus.CREATED).body(authorCreated);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {

        Author authorUpdated = authorService.update(id, author);

        return ResponseEntity.ok(authorUpdated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

        authorService.delete(id);
        return ResponseEntity.noContent().build();

    }

    // ============= Endpoints para manejar la relación con Books =============

    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Long authorId) {

        List<Book> books = authorService.getBooksByAuthor(authorId);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/{authorId}/books")
    public ResponseEntity<Author> addBookToAuthor(
            @PathVariable Long authorId,
            @RequestBody Book book) {

        Author author = authorService.addBookToAuthor(authorId, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @DeleteMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<Author> removeBookFromAuthor(
            @PathVariable Long authorId,
            @PathVariable Long bookId) {

        Author author = authorService.removeBookFromAuthor(authorId, bookId);
        return ResponseEntity.ok(author);
    }

}
