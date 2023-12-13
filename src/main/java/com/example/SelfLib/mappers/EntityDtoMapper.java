package com.example.SelfLib.mappers;

import com.example.SelfLib.domain.dto.AuthorDto;
import com.example.SelfLib.domain.entities.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    AuthorDto authorEntityToDto(AuthorEntity entity);

    AuthorEntity authorDtoToEntity(AuthorDto authorDto);
}
