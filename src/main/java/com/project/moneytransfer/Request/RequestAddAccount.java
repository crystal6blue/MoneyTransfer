package com.project.moneytransfer.Request;

import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import com.project.moneytransfer.Models.Customer;

public class RequestAddAccount {
    private Long accountId;

    private AccountType accountType;

    private AccountStatus accountStatus;

    private Customer customer;
}
