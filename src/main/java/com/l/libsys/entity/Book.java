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

    @Column(name="isbn")
    private String ISBN;

    @Column(name="title")
    private String tilte;

    @Column(name="author")
    private String author;

    @Column(name="availability")
    private byte[] availability;
}
