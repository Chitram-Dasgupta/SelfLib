package com.example.SelfLib.repositories;

import com.example.SelfLib.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, String> {
    List<BookEntity> findByAuthorEntity_Id(Long authorId);
}
