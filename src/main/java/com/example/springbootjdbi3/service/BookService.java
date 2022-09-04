package com.example.springbootjdbi3.service;


import com.example.springbootjdbi3.entities.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);

    List<Book> getAll();

    Book getOne(long id);

    int deleteById(long id);

}
