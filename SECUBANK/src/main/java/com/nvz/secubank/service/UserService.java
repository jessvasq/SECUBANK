package com.nvz.secubank.service;

import com.nvz.secubank.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    boolean userExistsByEmail(String email);
    boolean userExistsByUsername(String userName);
    void saveUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long userId);
}
