package com.l.libsys.service;

import com.l.libsys.entity.Book;
import com.l.libsys.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;

    public BookServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }
    @Override
    public Book createNewBook(Book book) {
        Optional<Book> existingBook = bookRepo.findByIsbn(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new RuntimeException("A Book with ISBN: " + book.getIsbn() + " already exists");
        }
        return bookRepo.save(book);
    }
}
   /* @Override
    public Book createNewBook(Book book) {
        Optional<Book> existingBook = bookRepo.findById(book.getId());
        if(existingBook.isPresent()){
            throw new RuntimeException("A Book with Index No :"+ book.getId() +" already exists");
        }
        return bookRepo.save(book);
}
*/