package com.l.libsys.service;

import com.l.libsys.entity.Book;
import com.l.libsys.repo.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;
    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
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

        return bookRepo.findByIsbn(isbn).map(existingBook -> {
            existingBook.setPublisher(updatedBook.getPublisher());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setTitle(updatedBook.getTitle());
            // Book save = BookRepository.<Book>save(existingBook);
            return bookRepo.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book with ISBN " + isbn + " not found"));
    }

    @Override
    @Transactional

    public Optional<Book> deleteBookByIsbn(String isbn) {
        return bookRepo.findByIsbn(isbn).map(Book -> {
            bookRepo.delete(Book);
            logger.info("Deleted Student with ISBN: {}", isbn);
            return Book;
        });
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