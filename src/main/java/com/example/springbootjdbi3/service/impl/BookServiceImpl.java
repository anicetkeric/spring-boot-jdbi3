package com.example.springbootjdbi3.service.impl;

import com.example.springbootjdbi3.entities.Book;
import com.example.springbootjdbi3.exception.DataNotFoundException;
import com.example.springbootjdbi3.repositories.BookRepository;
import com.example.springbootjdbi3.service.BookService;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(Jdbi jdbi) {
        this.repository = jdbi.onDemand(BookRepository.class);
    }

    @Override
    public Book create(Book book) {
        return getOne(repository.insert(book));
    }

    @Override
    public List<Book> getAll(){
        return repository.findAll();
    }

    @Override
    public Book getOne(long id) {
        Book book = repository.findById(id);
        if(ObjectUtils.isEmpty(book)){
           throw new DataNotFoundException(MessageFormat.format("Book id {0} not found", String.valueOf(id)));
        }
        return book;
    }

    @Override
    public int deleteById(long id) {
        return repository.deleteById(id);
    }

}