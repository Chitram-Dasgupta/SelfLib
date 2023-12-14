package com.example.SelfLib.controllers;

import com.example.SelfLib.domain.dto.AuthorDto;
import com.example.SelfLib.domain.dto.BookDto;
import com.example.SelfLib.domain.entities.BookEntity;
import com.example.SelfLib.mappers.EntityDtoMapper;
import com.example.SelfLib.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("books/{isbn}")
public class BookController {
    private final BookService bookService;
    private final EntityDtoMapper entityDtoMapper;

    public BookController(BookService bookService, EntityDtoMapper entityDtoMapper) {
        this.bookService = bookService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @GetMapping("")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> book = bookService.findOne(isbn);

        return book.map(bookEntity -> {
            BookDto bookDto = entityDtoMapper.bookEntityToDto(bookEntity);
            return new ResponseEntity<>(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
