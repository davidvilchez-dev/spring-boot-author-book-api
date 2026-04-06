package com.david.springboot.springboot_author_book_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.springboot.springboot_author_book_api.entity.Author;
import com.david.springboot.springboot_author_book_api.entity.Book;
import com.david.springboot.springboot_author_book_api.exception.ResourceNotFoundException;
import com.david.springboot.springboot_author_book_api.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {

        List<Author> authors = authorRepository.findAll();
        return authors;
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(Long id) {

        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

    }

    @Transactional
    @Override
    public Author create(Author author) {

        if (author.getBooks() != null && !author.getBooks().isEmpty()) {
            List<Book> books = new ArrayList<>(author.getBooks());
            books.forEach(book -> author.addBook(book));
        }

        return authorRepository.save(author);

    }

    @Transactional
    @Override
    public Author update(Long id, Author author) {

        Author authorExist = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

        authorExist.setName(author.getName());
        authorExist.setEmail(author.getEmail());
        authorExist.setBiography(author.getBiography());

        Author authorUpdated = authorRepository.save(authorExist);

        return authorUpdated;
    }

    @Transactional
    @Override
    public void delete(Long id) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

        authorRepository.delete(author);

    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getBooksByAuthor(Long authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

        return author.getBooks();
    }

}
