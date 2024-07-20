package com.nvz.secubank.service.impl;

import com.nvz.secubank.dto.UserDto;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean userExistsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent(); //returns true if there's a value present
    }

    @Override
    public boolean userExistsByUsername(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setProfilePicture(userDto.getProfilePicture());
        user.setLanguage(userDto.getLanguage());
        user.setCreatedAt(LocalDateTime.now());
        user.setTimeZone(ZoneId.systemDefault());
        user.setSsn(userDto.getSsn());
        user.setAccountType(userDto.getAccountType());

        //persist to db
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        //findById returns an Optional<User>, therefore we use .orElse to return null if the user is not found
          User user = userRepository.findById(id).orElse(null);
          user.setFirstName(userDto.getFirstName());
          user.setLastName(userDto.getLastName());
          user.setUserName(userDto.getUserName());

          //encode password if it has changed
        if (!user.getPassword().equals(userDto.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setProfilePicture(userDto.getProfilePicture());
        user.setLanguage(userDto.getLanguage());

        userRepository.save(user);
        return convertEntityToDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setProfilePicture(user.getProfilePicture());
        userDto.setLanguage(user.getLanguage());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setTimeZone(user.getTimeZone());
        return userDto;
    }
}
