package com.nvz.secubank.config;

import com.nvz.secubank.entity.User;
import com.nvz.secubank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //inject repository
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    Finds user by username and loads the data needed for authentication
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //fetch the user
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        //converts user entity to user details
        return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                .password(user.getPassword()) //creates a userDetails object
                .authorities("ROLE_USER") //sets the authorities for the user
                .accountExpired(false) //account is not expired
                .accountLocked(false) //account is not locked
                .credentialsExpired(false) //indicates that the password is not expired
                .disabled(false) //account is not disabled
                .build(); //builds and returns the 'userDetails' object
        }
}
