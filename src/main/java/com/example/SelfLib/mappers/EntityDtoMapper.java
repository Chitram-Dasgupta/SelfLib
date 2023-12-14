package com.example.SelfLib.mappers;

import com.example.SelfLib.domain.dto.AuthorDto;
import com.example.SelfLib.domain.dto.BookDto;
import com.example.SelfLib.domain.entities.AuthorEntity;
import com.example.SelfLib.domain.entities.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    AuthorDto authorEntityToDto(AuthorEntity authorEntity);

    AuthorEntity authorDtoToEntity(AuthorDto authorDto);

    BookDto bookEntityToDto(BookEntity bookEntity);

    BookEntity bookDtoToEntity(BookDto bookDto);
}
