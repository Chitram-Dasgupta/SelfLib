package com.example.SelfLib.services.impl;

import com.example.SelfLib.domain.entities.BookEntity;
import com.example.SelfLib.repositories.BookRepository;
import com.example.SelfLib.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<BookEntity> listBooks(Long authorId) {
        return bookRepository.findByAuthorEntity_Id(authorId);
    }

    @Override
    public BookEntity save(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean exists(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity partialUpdate(BookEntity bookEntity) {
        return bookRepository.findById(bookEntity.getIsbn()).map(book -> {
            Optional.ofNullable(bookEntity.getName()).ifPresent(book::setName);
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book does not exist"));
    }

    @Override
    public void delete(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
