package com.example.SelfLib.repositories;

import com.example.SelfLib.domain.entities.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
}
