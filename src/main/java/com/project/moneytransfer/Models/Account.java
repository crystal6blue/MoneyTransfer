package com.project.moneytransfer.Models;

import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// Account for a customer
// Auto - generated getters and setters.
// Defining constructors without parameters and only with specific parameters
// And, Defining this class as Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Account {
    // The ID for account. Which is unique and auto - generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;

    // Account type
    // This attribute might have names based on AccountType enum
    // it will be stored in the database as a string type
    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "account_type")
    private AccountType accountType;

    // Current balance of The account
    // At the beginning, it will be initialized as ZERO
    @NonNull
    @Column(name = "current_balance")
    private BigDecimal currentBalance = BigDecimal.ZERO;

    // This will store the time when account opened
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_opened", updatable = false)
    @CreatedDate
    private LocalDate dateOpened;

    // It will store the time when this account will be INACTIVE
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_closed", updatable = false)
    private LocalDate dateClosed;

    // Account status
    // It will be stored as a string type
    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "account_status")
    private AccountStatus accountStatus;

    // Automatically initializing date close after date open does
    // Every account's life span if 6 year
    @PrePersist
    protected void setDateClosed() {
        dateClosed = dateOpened.plusYears(6);
    }

    // Customer can have multiple account. account can be related to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // We can use one account for many operations
    @OneToMany(mappedBy = "account")
    private List<Transaction> transaction;
}
