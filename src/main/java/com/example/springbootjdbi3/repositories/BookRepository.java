package com.example.springbootjdbi3.repositories;

import com.example.springbootjdbi3.entities.Book;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h2>BookRepository</h2>
 *
 * @author aek
 */
@Repository
@RegisterBeanMapper(Book.class)
public interface BookRepository {
    @SqlQuery("select * from book")
    List<Book> findAll();

    @SqlQuery("select * from book where id = :id")
    Book findById(@Bind("id") long id);

    @Transaction
    @SqlUpdate("delete from book where id = :id")
    int deleteById(@Bind("id") long id);

    @Transaction
    @SqlUpdate("update book set title = :title, page=:page , isbn=:isbn , description=:description , price=:price where id = :id")
    int update(@BindBean Book book);

    @Transaction
    @GetGeneratedKeys
    @SqlUpdate("insert into book (title, page, isbn, description, price) values (:title, :page, :isbn, :description, :price)")
    int insert(@BindBean Book book);
}
