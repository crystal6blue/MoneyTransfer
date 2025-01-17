package com.project.moneytransfer.Service.AccountService;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Models.Account;
import com.project.moneytransfer.Models.Transaction;
import com.project.moneytransfer.Request.RequestAddAccount;
import com.project.moneytransfer.Respository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public AccountDto getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Account with id " + accountId + " not found"));
        return getAccountDto(account);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(()-> new ResourceNotFoundException("Account with id " + accountId + " not found"));
    }

    @Override
    public void addNewAccount(RequestAddAccount account) {
        Account newAccount = createAccount(account);
        accountRepository.save(newAccount);
    }

    private Account createAccount(RequestAddAccount account) {
        Account newAccount = new Account();
        modelMapper.map(account, newAccount);
        return newAccount;
    }

    @Override
    public void updateAccountStatus(Long accountId, AccountStatus status) {
        Account account = getAccountById(accountId);
        account.setAccountStatus(status);
    }

    @Override
    public void updateAccountBalance(Long accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        account.setCurrentBalance(account.getCurrentBalance().add(amount));
    }

    @Override
    public void updateAccountType(Long accountId, AccountType type) {
        Account account = getAccountById(accountId);
        account.setAccountType(type);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getTransaction)
                .orElseThrow(()-> new ResourceNotFoundException("Account with id " + accountId + " not found"));
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    private AccountDto getAccountDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }
}
