package com.l.libsys.repo;

import com.l.libsys.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book , Integer> {
    Optional <Book> findByIsbn(String isbn);
}
