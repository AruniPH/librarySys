package com.l.libsys.service;

import com.l.libsys.entity.Book;
import com.l.libsys.repo.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;
    // Constructor injection of BookRepository
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

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    // New method: Search Book by isbn
    @Override
    public Optional<Book> getBookDetailsByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn); // Use bookRepo
    }

    // New method: Update book details by isbn
    @Override
    @Transactional
    public Book updateBookByIsbn(String isbn, Book updatedBook) {
        //BookRepository BookRepo = null;
        return bookRepo.findByIsbn(isbn).map(existingBook -> {
            existingBook.setAvailability(updatedBook.getAvailability());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setTitle(updatedBook.getTitle());
           // Book save = BookRepository.<Book>save(existingBook);
            return bookRepo.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book with ISBN " + isbn + " not found"));
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