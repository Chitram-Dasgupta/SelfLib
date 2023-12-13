package com.example.SelfLib.services;

import com.example.SelfLib.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    AuthorEntity save(AuthorEntity authorEntity);
}
