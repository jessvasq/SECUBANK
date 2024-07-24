package com.nvz.secubank.service.impl;
import com.nvz.secubank.dto.UserDto;
import com.nvz.secubank.dto.UserDtoUpdate;
import com.nvz.secubank.entity.Role;
import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.RoleRepository;
import com.nvz.secubank.repository.UserRepository;
import com.nvz.secubank.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Override UserService methods and provide business logic
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @param userDto create a User with the given data
     * Set role based on registration and persist to the DB
     */
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

        // set role based on registration
        String roleName;
        if (userDto.isAdminRegistration()){
            roleName = "ROLE_ADMIN";
        } else {
            roleName = "ROLE_USER";
        }

        //check if role exists
        Role role = roleRepository.findRoleByName((roleName));
        if (role == null){
            role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }

        user.setRoles(Collections.singletonList(role));
        //persist to db
        userRepository.save(user);
    }

    /**
     * @return a list of all users
     * Access allowed to 'ADMIN' users only
     */
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user)).collect(Collectors.toList());
    }

    /**
     * @param userId Fetch user by its id
     * @param userDto Update user with input data
     * @return userDto and persist to the DB
     */
    @Override
    public UserDto updateUser(Long userId, UserDtoUpdate userDto) {
        //findById returns an Optional<User>, therefore we use .orElse to return null if the user is not found
          User user = userRepository.findByUserId(userId);
          user.setUserName(userDto.getUserName());
          user.setEmail(userDto.getEmail());

//          //encode password if it has changed
//        if (!user.getPassword().equals(userDto.getPassword())) {
//            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        }

        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setProfilePicture(userDto.getProfilePicture());
        user.setLanguage(userDto.getLanguage());

        userRepository.save(user);
        return convertEntityToDto(user);
    }

    /**
     * @param userId Delete user by its id
     */
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * @param email Fetch user by its email
     * @return user associated to the email
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @param user takes a User object to be converted to a DTO
     * @return userDTO
     */
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
