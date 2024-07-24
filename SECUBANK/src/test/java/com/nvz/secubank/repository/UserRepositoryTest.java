package com.nvz.secubank.repository;

import com.nvz.secubank.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    void testFindByEmail() {
        String email = "john@doe.com";

        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setUserName("johnDoe");
        newUser.setEmail(email);
        newUser.setPassword("password");
        newUser.setPhoneNumber("1234567890");
        newUser.setAddress("NYC");
        newUser.setLanguage("english");
        newUser.setSsn("222-22-2222");
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setTimeZone(ZoneId.systemDefault());

        userRepository.save(newUser);

        User foundUser = userRepository.findByEmail(email);

        assertNotNull(foundUser);
        assertEquals(email, foundUser.getEmail());

    }

    @Test
    void testFindByUserId() {
        String email = "john@doe.com";

        User newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Doe");
        newUser.setUserName("johnDoe");
        newUser.setEmail("john@doe.com");
        newUser.setPassword("password");
        newUser.setPhoneNumber("1234567890");
        newUser.setAddress("NYC");
        newUser.setLanguage("english");
        newUser.setSsn("222-22-2222");
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setTimeZone(ZoneId.systemDefault());

        userRepository.save(newUser);

        User foundUser = userRepository.findByEmail(email);
        Long userId = foundUser.getUserId();

        assertNotNull(userRepository.findByUserId(userId));
    }
}