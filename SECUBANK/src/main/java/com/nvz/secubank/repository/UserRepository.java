package com.nvz.secubank.repository;

import com.nvz.secubank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Class that extends JpaRepository which provides Built-in CRUD operations methods
 * Custom methods
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserId(Long id);
}
