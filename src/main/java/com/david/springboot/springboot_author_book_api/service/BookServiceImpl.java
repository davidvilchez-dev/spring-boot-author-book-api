package com.david.springboot.springboot_author_book_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.springboot.springboot_author_book_api.dto.BookRequest;
import com.david.springboot.springboot_author_book_api.dto.BookResponse;
import com.david.springboot.springboot_author_book_api.entity.Author;
import com.david.springboot.springboot_author_book_api.entity.Book;
import com.david.springboot.springboot_author_book_api.exception.ResourceNotFoundException;
import com.david.springboot.springboot_author_book_api.mapper.BookMapper;
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
    public List<BookResponse> findAll() {

        return bookRepository.findAll().stream()
                .map(BookMapper::toBookResponse)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    @Override
    public BookResponse findById(Long id) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
        BookResponse bookResponse = BookMapper.toBookResponse(book);
        return bookResponse;
    }

    @Transactional
    @Override
    public BookResponse create(BookRequest bookRequest) {

        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(bookRequest.getTitle());
        book.setGender(bookRequest.getGender());
        book.setPublished(bookRequest.getPublished());

        Book bookSaved = bookRepository.save(book);
        BookResponse bookResponse = BookMapper.toBookResponse(bookSaved);
        return bookResponse;

    }

    @Transactional
    @Override
    public BookResponse update(Long id, BookRequest bookRequest) {
        Book bookExist = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));

        bookExist.setTitle(bookRequest.getTitle());
        bookExist.setGender(bookRequest.getGender());
        bookExist.setPublished(bookRequest.getPublished());
        Book bookUpdated = bookRepository.save(bookExist);

        BookResponse bookResponse = BookMapper.toBookResponse(bookUpdated);

        return bookResponse;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado"));
        bookRepository.delete(book);
    }

}
