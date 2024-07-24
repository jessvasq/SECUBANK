package com.nvz.secubank.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

/**
 * User entity stores user information
 * ManyToMany relationship with Roles
 * OneToMany relationship with Account
 * OneToMany relationship with Notification
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 50, unique = true)
    private String userName;
    @Column(nullable = false)
    private String phoneNumber;
    private String profilePicture;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true, length = 12)
    private String ssn;
    @Column(nullable = false)
    private String address;
    private String language;
    private ZoneId timeZone;
    private LocalDateTime createdAt;

    //entities are loaded immediately with the parent entity
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
    private List<Recipient> recipients;

}
