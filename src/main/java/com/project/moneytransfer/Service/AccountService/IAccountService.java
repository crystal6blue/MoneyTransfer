package com.project.moneytransfer.Service.AccountService;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import com.project.moneytransfer.Models.Account;
import com.project.moneytransfer.Models.Transaction;
import com.project.moneytransfer.Request.RequestAddAccount;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    AccountDto getAccount(Long accountId);

    void addNewAccount(RequestAddAccount account);

    void updateAccountStatus(Long accountId, AccountStatus status);

    void updateAccountBalance(Long accountId, BigDecimal amount);

    void updateAccountType(Long accountId, AccountType type);

    List<Transaction> getTransactionsByAccountId(Long accountId);

    List<Account> getAllAccounts();
}
