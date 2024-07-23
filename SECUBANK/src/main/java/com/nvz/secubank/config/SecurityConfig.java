package com.nvz.secubank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } //uses BCrypt hashing function

    /*
    This method takes a 'HttpSecurity' object as a param and returns a 'SecurityFilterChain' which applies security to HTTP requests
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/public/**").permitAll()
                        .requestMatchers("/register/**").permitAll() //anyone can access this endpoint
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/accounts/**").hasRole("USER")//restrict access to the '/accounts' endpoint to users with 'USER' role
                        .anyRequest().authenticated() // all other requests can only be accessed by authenticated users
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")//form will be submitted to this endpoint
                        .defaultSuccessUrl("/users/accounts", true) //users will be redirected to this endpoint after successful authentication
                        .permitAll()//anyone can access the login page
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//when a user access this endpoint, they will be logged out
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                        );
                return http.build(); //builds the 'HttpSecurity' config and returns the 'securityFilterChain' object
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
