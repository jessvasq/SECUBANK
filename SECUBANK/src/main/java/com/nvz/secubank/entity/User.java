package com.nvz.secubank.entity;

import com.nvz.secubank.entity.Account;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

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
    @Column(nullable = false, unique = true, length = 9)
    private String ssn;
    @Column(nullable = false)
    private String address;
    private String language;
    private ZoneId timeZone;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private String accountType;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    @OneToMany(mappedBy= "user", cascade = CascadeType.ALL)
    private List<Recipient> recipients;

}
