package com.example.SelfLib.services.impl;

import com.example.SelfLib.domain.entities.AuthorEntity;
import com.example.SelfLib.repositories.AuthorRepository;
import com.example.SelfLib.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public AuthorEntity partialUpdate(AuthorEntity authorEntity) {
        return authorRepository.findById(authorEntity.getId()).map(author -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(author::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(author::setAge);
            return authorRepository.save(author);
        }).orElseThrow(() -> new RuntimeException("Author does not exist"));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
