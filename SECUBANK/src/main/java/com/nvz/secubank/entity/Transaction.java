package com.nvz.secubank.entity;

import com.nvz.secubank.entity.enumClasses.TransactionStatus;
import com.nvz.secubank.entity.enumClasses.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Transaction entity stores all transactions made by the user
 * ManyToOne relationship with Account entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private String fromAccountNumber;
    @Column(nullable = false)
    private String toAccountNumber;

    private String toUserEmail;

    @Column(nullable = false)
    private BigDecimal amount;
    private LocalDateTime date; //transaction date
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;


    @ManyToOne(fetch = FetchType.LAZY) //entities should be loaded when requested rather than at the time the entity is created
    @JoinColumn(name = "account_id", nullable=false)
    private Account account;

}


