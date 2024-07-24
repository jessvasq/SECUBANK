package com.nvz.secubank.repository;

import com.nvz.secubank.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Class that extends JpaRepository which provides Built-in CRUD operations methods
 * Custom methods
 */
@Repository
public interface RecipientRepository extends JpaRepository<Recipient, Long> {
}
