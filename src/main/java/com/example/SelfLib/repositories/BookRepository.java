package com.example.SelfLib.repositories;

import com.example.SelfLib.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, String> {
}
