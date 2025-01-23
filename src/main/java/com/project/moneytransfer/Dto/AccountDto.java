package com.project.moneytransfer.Dto;

import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountDto {

    private Long accountId;

    private AccountType accountType;

    private BigDecimal currentBalance;

    private AccountStatus accountStatus;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOpened;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateClosed;

    private Long customerId;
}
