package com.project.moneytransfer.Service.AccountService;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Models.Account;
import com.project.moneytransfer.Models.Customer;
import com.project.moneytransfer.Models.Transaction;

import com.project.moneytransfer.Respository.AccountRepository;
import com.project.moneytransfer.Respository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

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
    public void addNewAccount(Long customerId) {
        createAccount(customerId);
    }

    public Account createAccount(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer with id " + customerId + " not found"));

        Account newAccount = new Account();
        newAccount.setAccountType(customerRepository.findById(customerId).get()
                .getAccountList()
                .stream().filter(account -> account.getAccountType()==AccountType.MAIN)
                .findFirst()
                        .map(account -> AccountType.ORDINARY)
                .orElse(AccountType.MAIN));
        newAccount.setAccountStatus(AccountStatus.ACTIVE);

        customer.getAccountList().add(newAccount);
        customerRepository.save(customer);
        return newAccount;
    }

    @Override
    public void updateAccountStatus(Long accountId, AccountStatus status) {
        Account account = getAccountById(accountId);
        account.setAccountStatus(status);
        accountRepository.save(account);
    }

    @Override
    public void updateAccountBalance(Long accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        account.setCurrentBalance(account.getCurrentBalance().add(amount));
        accountRepository.save(account);
    }

    @Override
    public void updateAccountType(Long accountId, AccountType type) {
        Account account = getAccountById(accountId);
        account.setAccountType(type);
        accountRepository.save(account);
    }

    @Override
    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getTransaction)
                .map(transactions -> transactions
                        .stream()
                        .map(this::getTransactionDto)
                        .collect(Collectors.toList()))
                .orElseThrow(()-> new ResourceNotFoundException("Account with id " + accountId + " not found"));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream().map(this::getAccountDto).collect(Collectors.toList());
    }

    private AccountDto getAccountDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }

    private TransactionDto getTransactionDto(Transaction transaction) { return modelMapper.map(transaction, TransactionDto.class);}
}
