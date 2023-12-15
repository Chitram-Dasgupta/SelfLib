package com.example.SelfLib.controllers;

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

    @PutMapping("")
    public ResponseEntity<BookDto> fullUpdateBook(@PathVariable("isbn") String isbn, @Valid @RequestBody BookDto bookDto) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);

        if (foundBook.isPresent()) {
            BookEntity bookEntity = entityDtoMapper.bookDtoToEntity(bookDto);

            // We do not let the client inadvertently modify the author resource when trying
            // to update the book resource
            if (!bookEntity.getAuthorEntity().equals(foundBook.get().getAuthorEntity())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            BookEntity updatedBookEntity = bookService.save(bookEntity);
            return new ResponseEntity<>(entityDtoMapper.bookEntityToDto(updatedBookEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("")
    public ResponseEntity<BookDto> partialUpdateBook(@PathVariable("isbn") String isbn, @Valid @RequestBody BookDto bookDto) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);

        if (foundBook.isPresent()) {
            BookEntity bookEntity = entityDtoMapper.bookDtoToEntity(bookDto);

            // We do not let the client inadvertently modify the author resource when trying
            // to update the book resource
            if (!bookEntity.getAuthorEntity().equals(foundBook.get().getAuthorEntity())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            bookEntity.setIsbn(isbn);
            BookEntity updatedBookEntity = bookService.partialUpdate(bookEntity);
            return new ResponseEntity<>(entityDtoMapper.bookEntityToDto(updatedBookEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteBook(@PathVariable("isbn") String isbn) {
        bookService.delete(isbn);
        return ResponseEntity.noContent().build();
    }
}
