package com.nvz.secubank.entity;

import com.nvz.secubank.entity.enumClasses.TransactionStatus;
import com.nvz.secubank.entity.enumClasses.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal amount;
    private LocalDateTime date; //transaction date
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable=false)
    private Account account;

}


