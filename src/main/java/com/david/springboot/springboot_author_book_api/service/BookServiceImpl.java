package com.david.springboot.springboot_author_book_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.springboot.springboot_author_book_api.entity.Author;
import com.david.springboot.springboot_author_book_api.entity.Book;
import com.david.springboot.springboot_author_book_api.exception.ResourceNotFoundException;
import com.david.springboot.springboot_author_book_api.repository.AuthorRepository;
import com.david.springboot.springboot_author_book_api.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();

    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        return book;
    }

    @Transactional
    @Override
    public Book create(Book book, Long authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

        author.addBook(book);
        return bookRepository.save(book);

    }

    @Transactional
    @Override
    public Book update(Long id, Book book) {
        Book bookExist = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        bookExist.setTitle(book.getTitle());
        bookExist.setGender(book.getGender());
        bookExist.setPublished(book.getPublished());
        Book bookUpdated = bookRepository.save(bookExist);

        return bookUpdated;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
        bookRepository.delete(book);
    }

}
