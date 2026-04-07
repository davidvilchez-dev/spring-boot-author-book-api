package com.david.springboot.springboot_author_book_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.david.springboot.springboot_author_book_api.dto.AuthorRequest;
import com.david.springboot.springboot_author_book_api.dto.AuthorResponse;
import com.david.springboot.springboot_author_book_api.dto.BookResponse;
import com.david.springboot.springboot_author_book_api.entity.Author;
import com.david.springboot.springboot_author_book_api.entity.Book;
import com.david.springboot.springboot_author_book_api.exception.ResourceNotFoundException;
import com.david.springboot.springboot_author_book_api.mapper.AuthorMapper;
import com.david.springboot.springboot_author_book_api.mapper.BookMapper;
import com.david.springboot.springboot_author_book_api.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorResponse> findAll() {

        return authorRepository.findAll().stream()
                .map(AuthorMapper::toAuthorResponse)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    @Override
    public AuthorResponse findById(Long id) {

        return authorRepository.findById(id)
                .map(AuthorMapper::toAuthorResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

    }

    @Transactional
    @Override
    public AuthorResponse create(AuthorRequest authorRequest) {

        Author author = authorRepository.save(AuthorMapper.toAuthorEntity(authorRequest));

        AuthorResponse authorResponse = AuthorMapper.toAuthorResponse(author);

        return authorResponse;

    }

    @Transactional
    @Override
    public AuthorResponse update(Long id, AuthorRequest authorRequest) {

        Author authorExist = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

        authorExist.setName(authorRequest.getName());
        authorExist.setEmail(authorRequest.getEmail());
        authorExist.setBiography(authorRequest.getBiography());

        Author authorUpdated = authorRepository.save(authorExist);

        AuthorResponse authorResponse = AuthorMapper.toAuthorResponse(authorUpdated);

        return authorResponse;
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
    public List<BookResponse> getBooksByAuthor(Long authorId) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado"));

        return author.getBooks().stream()
                .map(BookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

}
