package com.project.moneytransfer.Models;

import com.project.moneytransfer.Enums.PaymentTypes;
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

    // Payment type
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", updatable = false)
    private PaymentTypes paymentType;

    // transaction_date
    @Column(name = "transaction_date")
    @CreatedDate
    private LocalDateTime transactionDate;

    // Many transaction can be related to one account
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "account_id")
    private Account account;
}
