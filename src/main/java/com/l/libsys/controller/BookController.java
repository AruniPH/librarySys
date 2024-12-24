package com.l.libsys.controller;

import com.l.libsys.entity.Book;
import com.l.libsys.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookservice;
    private Book updatedBook;

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
        //String isbn;
        List<Book> books = bookservice.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    // New endpoint: Get book by isbn
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        return bookservice.getBookDetailsByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // New endpoint: Update book by isbn
    @PutMapping("/update/isbn/{isbn}")
    public ResponseEntity<Book> updateBookByIsbn(
            @PathVariable String isbn,
            @RequestBody Book updatedBook) {
        try {
          
            Book updated;
            updated = bookservice.updateBookByIsbn(isbn, updatedBook);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete a book by isbn
    @DeleteMapping("/delete/isbn/{isbn}")
    public ResponseEntity<String> deleteBookByIsbn(@PathVariable String isbn) {
        Optional<Book> Book = bookservice.deleteBookByIsbn(isbn);
        if (Book.isPresent()) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ISBN " + isbn + " not found");
        }
    }
}
