package com.project.moneytransfer.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.moneytransfer.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Transaction table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long transactionId;

    // Transaction status


    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    // Amount
    // of money that going to be transferred
    @Column(name = "amount")
    private BigDecimal Amount;

    // transaction_date
    @Column(name = "transaction_date")
    @CreatedDate
    private LocalDateTime transactionDate;

    // Many transaction can be related to one account
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private PhoneNumberPayment phoneNumberPayment;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private SteamPayment steamPayment;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private ToAnotherClientPayment toAnotherClientPayment;
}
