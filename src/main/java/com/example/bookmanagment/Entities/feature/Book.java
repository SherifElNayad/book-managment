package com.example.bookmanagment.Entities.feature;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "book", schema = "book-management")
@Entity
public class Book  {
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

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    @JsonIgnore
    private List<BorrowRequest> borrowRequest;


}
