package com.project.moneytransfer.Service.AccountService;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import com.project.moneytransfer.Exceptions.InvalidRequestException;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Models.Account;
import com.project.moneytransfer.Models.Customer;
import com.project.moneytransfer.Models.Transaction;

import com.project.moneytransfer.Respository.AccountRepository;
import com.project.moneytransfer.Respository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


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

        return accountRepository.findById(accountId)
            .map(this::getAccountDto)
            .orElseThrow(()-> new ResourceNotFoundException("Account with id " + accountId + " not found"));
    }

    public Account getAccountById(Long accountId) {

        return accountRepository.findById(accountId)
            .orElseThrow(()-> new ResourceNotFoundException("Account with id " + accountId + " not found"));
    }

    @Override
    public void addNewAccount(Long customerId) {

        createAccount(customerId);
    }

    @Override
    public void blockAccount(Long accountId) {
        Account account = getAccountById(accountId);

        if(account.getAccountStatus() == AccountStatus.INACTIVE){
            throw new InvalidRequestException("Account with id " + accountId + " is already blocked");
        }
        
        account.setAccountStatus(AccountStatus.INACTIVE);

        accountRepository.save(account);
    }

    @Override
    public void unblockAccount(Long accountId) {

        Account account = getAccountById(accountId);

        if(account.getAccountStatus() == AccountStatus.ACTIVE){
            throw new InvalidRequestException("the account with id " + accountId + " is already unblocked");
        }

        account.setAccountStatus(AccountStatus.ACTIVE);

        accountRepository.save(account);
    }


    public void createAccount(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("the customer with id " + customerId + " not found"));

        if (customer.getAccountList().size() >= 3) {
            throw new InvalidRequestException("Customer cannot have more than 3 accounts");
        }

        Account newAccount = new Account(AccountType.ORDINARY, AccountStatus.ACTIVE);

        newAccount.setCustomer(customer);
        customer.getAccountList().add(newAccount);

        accountRepository.save(newAccount);
        customerRepository.save(customer);
    }

    @Override
    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getTransaction)
                .map(transactions -> transactions
                        .stream()
                        .map(this::getTransactionDto)
                        .collect(Collectors.toList()))
                .orElseThrow(()-> new ResourceNotFoundException("The account with id " + accountId + " not found"));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll()
                .stream().map(this::getAccountDto)
                .collect(Collectors.toList());
    }

    private AccountDto getAccountDto(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        accountDto.setCustomerId(account.getCustomer().getCustomerId());
        return accountDto;
    }

    private TransactionDto getTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        transactionDto.setAccountId(transaction.getAccount().getAccountId());
        return transactionDto;
    }
}
