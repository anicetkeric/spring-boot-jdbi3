package com.example.springbootjdbi3.service;


import com.example.springbootjdbi3.entities.Author;

import java.util.List;

public interface AuthorService {

    Author create(Author author);

    List<Author> getAll();

    Author getOne(long id);

    int deleteById(long id);
}
