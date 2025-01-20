package com.project.moneytransfer.Dto;

import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountDto {
    private Long accountId;

    private AccountType accountType;

    private BigDecimal currentBalance;

    private AccountStatus accountStatus;

    private LocalDate dateOpened;

    private LocalDate dateClosed;
}
