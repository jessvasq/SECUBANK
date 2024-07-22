package com.nvz.secubank.service;

import com.nvz.secubank.dto.UserDto;
import com.nvz.secubank.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface UserService {
    void saveUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long userId);
    User findByEmail(String email);
}
