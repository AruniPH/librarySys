package com.l.libsys.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    // @Column(name="availability")
    //private byte[] availability;
    //private boolean availability;

    @Column(name = "publisher")
    private String publisher;

    // Optional: Custom constructor for ease of use
    public Book(String isbn, String title, String author, String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }
}

