package com.nvz.secubank.entity;

import com.nvz.secubank.entity.enumClasses.AccountType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
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
    private LocalDate createdAt; //date account was created

    @Enumerated(EnumType.STRING) // enum values should be stored as strings in the db
    @Column(nullable=false, length=50)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable=false)
    private User owner;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Card card;
}

