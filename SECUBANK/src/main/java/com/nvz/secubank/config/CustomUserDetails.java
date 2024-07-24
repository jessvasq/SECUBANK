package com.nvz.secubank.config;

import com.nvz.secubank.entity.Role;
import com.nvz.secubank.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * provides user-specific info to SpringSecurity for authentication and authorization
 */
public class CustomUserDetails implements UserDetails {
    //contains user details
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * provides the authorites(roles and permissions) associated with the user.Ensures we provide the necessary info to Spring Security
     * @return collection of GrantedAuthority objects representing the user's authorities
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //fetch a list of roles from the 'user' object
        List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        //add grantedAuthority(role/permission) for the user
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        //return a collection of GrantedAuthority objects representing the user's authorities
        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Override getUsername method
     * @return user's email instead of username
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
