package com.project.moneytransfer.Service.AccountService;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Enums.AccountType;

import java.util.List;

public interface IAccountService {
    AccountDto getAccount(Long accountId);

    void addNewAccount(Long customerId);

    void blockAccount(Long accountId);

    void unblockAccount(Long accountId);

    List<TransactionDto> getTransactionsByAccountId(Long accountId);

    List<AccountDto> getAllAccounts();
}
