package com.l.libsys.service;

import com.l.libsys.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Book createNewBook(Book book);
    List<Book> getAllBooks  ();
    Optional<Book> getBookDetailsByIsbn(String isbn);
    Book updateBookByIsbn(String isbn, Book updatedBook);
    Optional<Book> deleteBookByIsbn(String isbn);
}
