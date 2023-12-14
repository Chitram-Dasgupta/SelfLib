package com.example.SelfLib.controllers;

import com.example.SelfLib.domain.dto.BookDto;
import com.example.SelfLib.domain.entities.AuthorEntity;
import com.example.SelfLib.domain.entities.BookEntity;
import com.example.SelfLib.mappers.EntityDtoMapper;
import com.example.SelfLib.services.AuthorService;
import com.example.SelfLib.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("authors/{authorId}/books")
public class AuthorBookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final EntityDtoMapper entityDtoMapper;

    public AuthorBookController(BookService bookService, AuthorService authorService, EntityDtoMapper entityDtoMapper) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.entityDtoMapper = entityDtoMapper;
    }
    @GetMapping("/")
    public ResponseEntity<List<BookDto>> listBooks(@PathVariable("authorId") Long authorId) {
        Optional<AuthorEntity> foundAuthor = authorService.findOne(authorId);

        if (foundAuthor.isPresent()) {
            List<BookEntity> books = bookService.listBooks(authorId);
            List<BookDto> bookDtos = books.stream().map(entityDtoMapper::bookEntityToDto).toList();
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> createBook(@PathVariable("authorId") Long authorId,
                                              @Valid @RequestBody BookDto bookDto) {
        Optional<AuthorEntity> foundAuthor = authorService.findOne(authorId);

        if (foundAuthor.isPresent()) {
            BookEntity bookEntity = entityDtoMapper.bookDtoToEntity(bookDto);
            bookEntity.setAuthorEntity(foundAuthor.get());
            BookEntity savedBookEntity = bookService.save(bookEntity);
            return new ResponseEntity<>(entityDtoMapper.bookEntityToDto(savedBookEntity), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
