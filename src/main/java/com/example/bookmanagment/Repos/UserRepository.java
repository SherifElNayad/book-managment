package com.example.bookmanagment.Repos;

import com.example.bookmanagment.Entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String name);
    Optional<User> findByUsername(String name);
}
