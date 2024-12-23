package com.l.libsys.service;

import com.l.libsys.entity.Book;
import java.util.List;

public interface BookService {
    Book createNewBook(Book book);
    List<Book> getAllBooks  ();
}
