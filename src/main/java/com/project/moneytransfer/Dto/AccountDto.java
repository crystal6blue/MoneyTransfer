package com.project.moneytransfer.Dto;

import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import com.project.moneytransfer.Models.Customer;
import lombok.Data;

@Data
public class AccountDto {
    private Long accountId;

    private AccountType accountType;

    private AccountStatus accountStatus;

    private Customer customer;
}
