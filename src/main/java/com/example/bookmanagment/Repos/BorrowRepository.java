package com.example.bookmanagment.Repos;

import com.example.bookmanagment.Entities.feature.Book;
import com.example.bookmanagment.Entities.feature.BorrowRequest;
import com.example.bookmanagment.Entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRequest, Long> {
    Optional<BorrowRequest> findByIdAndUser(long id, User user);
    Optional<BorrowRequest> findByBookAndUser(Book book, User user);

    List<BorrowRequest> findByUser(User user);
}
