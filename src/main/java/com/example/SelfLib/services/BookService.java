package com.example.SelfLib.services;

import com.example.SelfLib.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookEntity> listBooks(Long authorId);

    BookEntity save(BookEntity bookEntity);

    Optional<BookEntity> findOne(String isbn);

    boolean exists(String isbn);

    BookEntity partialUpdate(BookEntity bookEntity);
}
