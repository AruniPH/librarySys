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

    @Column(name="isbn", unique=true, nullable = false)
    private String isbn;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    // @Column(name="availability")
    //private byte[] availability;
    //private boolean availability;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean availability;

    // Optional: Custom constructor for ease of use
    public Book(String isbn, String title, String author, boolean availability) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }
}
