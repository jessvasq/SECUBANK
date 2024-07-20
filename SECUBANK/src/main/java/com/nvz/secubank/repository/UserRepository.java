package com.nvz.secubank.repository;

import com.nvz.secubank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //optional is a container class that may or may not contain a null value
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
   // User findUserByUserName(String userName);
}
