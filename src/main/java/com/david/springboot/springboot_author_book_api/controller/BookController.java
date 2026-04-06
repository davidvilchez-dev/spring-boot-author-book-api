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

import com.david.springboot.springboot_author_book_api.dto.BookRequest;
import com.david.springboot.springboot_author_book_api.dto.BookResponse;
import com.david.springboot.springboot_author_book_api.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {

        List<BookResponse> books = bookService.findAll();

        return ResponseEntity.ok(books);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {

        BookResponse book = bookService.findById(id);

        return ResponseEntity.ok(book);

    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody BookRequest book) {

        BookResponse bookCreated = bookService.create(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookCreated);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody BookRequest book) {

        BookResponse bookUpdated = bookService.update(id, book);

        return ResponseEntity.ok(bookUpdated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        bookService.delete(id);

        return ResponseEntity.noContent().build();

    }
}
