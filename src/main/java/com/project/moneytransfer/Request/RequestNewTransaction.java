package com.project.moneytransfer.Request;

import com.project.moneytransfer.Enums.TransactionStatus;
import com.project.moneytransfer.Models.Account;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestNewTransaction {

    @NotNull
    // Transaction status
    private TransactionStatus transactionStatus;

    @NotNull
    // Amount
    // of money that going to be transferred
    private BigDecimal Amount;

    @NotNull
    private Account account;
}
