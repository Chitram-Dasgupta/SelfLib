package com.example.SelfLib.services;

import com.example.SelfLib.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {
    List<AuthorEntity> findAll();
}
