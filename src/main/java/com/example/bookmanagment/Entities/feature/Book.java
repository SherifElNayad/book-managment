package com.example.bookmanagment.Entities.feature;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "book", schema = "book-management")
@Entity
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private int quantity;

    @Column
    private String shortDescription;

    @Column(nullable = false)
    private boolean isAvailable;

    @Column
    private Instant publishDate;

    @ManyToOne
    private Category category;

}
