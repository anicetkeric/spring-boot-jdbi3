package com.example.springbootjdbi3.repositories;

import com.example.springbootjdbi3.entities.Author;
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
 * <h2>AuthorRepository</h2>
 * 
 * @author aek
 */
@Repository
@RegisterBeanMapper(Author.class)
public interface AuthorRepository {
    @SqlQuery("select * from author;")
    List<Author> findAll();
    @SqlQuery("select * from author where id = :id;")
    Author findById(@Bind("id") long id);

    @Transaction
    @SqlUpdate("delete from author where id = :id;")
    int deleteById(@Bind("id") long id);

    @Transaction
    @SqlUpdate("update author set lastname = :lastname, firstname=:firstname where id = :id;")
    int update(@BindBean Author author);

    @Transaction
    @GetGeneratedKeys
    @SqlUpdate("insert into author (lastname, firstname) values (:lastname, :firstname);")
    int insert(@BindBean Author author);
}
