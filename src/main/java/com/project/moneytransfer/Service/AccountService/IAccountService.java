package com.project.moneytransfer.Service.AccountService;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    AccountDto getAccount(Long accountId);

    void addNewAccount(Long customerId);

    void updateAccountStatus(Long accountId, AccountStatus status);

    void updateAccountBalance(Long accountId, BigDecimal amount);

    void updateAccountType(Long accountId, AccountType accountType);

    List<TransactionDto> getTransactionsByAccountId(Long accountId);

    List<AccountDto> getAllAccounts();
}
