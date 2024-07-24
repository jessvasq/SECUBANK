package com.nvz.secubank.service;

import com.nvz.secubank.dto.UserDto;
import com.nvz.secubank.dto.UserDtoUpdate;
import com.nvz.secubank.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Define methods and abstracts the business logic
 */
@Component
public interface UserService {
    void saveUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long userId, UserDtoUpdate userDtoUpdate);
    void deleteUser(Long userId);
    User findByEmail(String email);
}
