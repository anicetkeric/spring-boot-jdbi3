package com.example.springbootjdbi3.service.impl;

import com.example.springbootjdbi3.entities.Author;
import com.example.springbootjdbi3.exception.DataNotFoundException;
import com.example.springbootjdbi3.repositories.AuthorRepository;
import com.example.springbootjdbi3.service.AuthorService;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(Jdbi jdbi) {
        this.repository = jdbi.onDemand(AuthorRepository.class);
    }

    @Override
    public Author create(Author author) {
        return getOne(repository.insert(author));
    }

    @Override
    public List<Author> getAll(){
        return repository.findAll();
    }

    @Override
    public Author getOne(long id) {
        Author author = repository.findById(id);
        if(ObjectUtils.isEmpty(author)){
           throw new DataNotFoundException(MessageFormat.format("Author id {0} not found", String.valueOf(id)));
        }
        return author;
    }

    @Override
    public int deleteById(long id) {
        return repository.deleteById(id);
    }

}