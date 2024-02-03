package com.example.bookmanagment.Repos;

import com.example.bookmanagment.Entities.feature.Book;
import com.example.bookmanagment.Entities.feature.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(Category category);
}
