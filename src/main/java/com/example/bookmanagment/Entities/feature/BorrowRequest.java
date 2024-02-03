package com.example.bookmanagment.Entities.feature;

import com.example.bookmanagment.Entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "borrow_request", schema = "book-management")
@Entity
public class BorrowRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(nullable = false)
    private Instant startDate;
    @Column(nullable = false)
    private Instant endDate;
    @Column
    private boolean isApproved;
    @OneToOne
    private Book book;



}
