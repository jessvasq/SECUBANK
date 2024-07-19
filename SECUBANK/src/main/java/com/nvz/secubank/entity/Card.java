package com.nvz.secubank.entity;

import com.nvz.secubank.entity.enumClasses.CardStatus;
import com.nvz.secubank.entity.enumClasses.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table (name="card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    @Column(unique=true, length=15, nullable=false)
    private String cardNumber;
    @Column(length=15, nullable=false)
    private YearMonth expirationDate;
    @Column(length=15, nullable=false)
    private String securityCode;
    @Column(length=50, nullable=false)
    private String cardHolderName;
    private LocalDate issueDate;

    @Enumerated(EnumType.STRING)
    @Column(length=15, nullable=false)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private CardStatus status;

    //account linked to the card
    @OneToOne
    @JoinColumn(name="account_id", nullable = false)
    private Account account;
}
