package com.nvz.secubank.entity;

import com.nvz.secubank.entity.enumClasses.AccountType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Account entity stores different bank accounts
 * ManyToOne relationship with User
 * OneToMany relationship with Transaction
 * OneToOne relationship with Card
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(unique=true, nullable=false, length=12)
    private String accountNumber;
    @Column(nullable=false)
    private BigDecimal balance;
    @Column(nullable=false)
    private String currency;
    @Column(nullable=false)
    private BigDecimal interestRate;
    private LocalDateTime createdAt; //date account was created

    private BigDecimal prevBalance;

    @Enumerated(EnumType.STRING) // enum values should be stored as strings in the db
    @Column(nullable=false, length=50)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Card card;
}

