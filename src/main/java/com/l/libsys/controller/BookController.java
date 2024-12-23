package com.l.libsys.controller;

import com.l.libsys.entity.Book;
import com.l.libsys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookservice;

    @PostMapping("/add")
    public ResponseEntity<?> createBook(@RequestBody Book book) {

        try{
            Book addedBook = bookservice.createNewBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    // endpoint to get all books
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookservice.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

}
