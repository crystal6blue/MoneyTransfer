package com.project.moneytransfer.Service.Transaction;

import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.AccountType;
import com.project.moneytransfer.Enums.TransactionStatus;
import com.project.moneytransfer.Exceptions.InvalidRequestException;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Models.*;
import com.project.moneytransfer.Request.RequestNewTransaction;
import com.project.moneytransfer.Respository.AccountRepository;
import com.project.moneytransfer.Respository.PersonRepository;
import com.project.moneytransfer.Respository.TransactionRepository;
import com.project.moneytransfer.Service.AccountService.AccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Override
    public TransactionDto getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(this::getTransactionDto)
                .orElseThrow(()->new ResourceNotFoundException("Transaction not found"));
    }

    ///  -?- ???
    @Override
    public TransactionDto createTransaction(RequestNewTransaction transaction, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        Transaction newTransaction = getTransactionFromRequest(transaction);
        if(account.getAccountStatus() == AccountStatus.INACTIVE) {
            newTransaction.setTransactionStatus(TransactionStatus.FAILED);
        } else if (account.getCurrentBalance().subtract(newTransaction.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
            newTransaction.setTransactionStatus(TransactionStatus.DECLINED);
        }else{
            account.setCurrentBalance(account.getCurrentBalance().subtract(newTransaction.getAmount()));
        }

        account.getTransaction().add(newTransaction);
        accountRepository.save(account);
        return getTransactionDto(newTransaction);
    }

    private Transaction getTransactionFromRequest(RequestNewTransaction transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.getAmount());
        if(transaction.getPhoneNumberPayment() == null && transaction.getSteamPayment() != null && transaction.getToAnotherClientPayment() != null){
            newTransaction.setPhoneNumberPayment(new PhoneNumberPayment(transaction.getPhoneNumberPayment().getPhoneNumber()));
            newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        }else if(transaction.getPhoneNumberPayment() != null && transaction.getSteamPayment() == null && transaction.getToAnotherClientPayment() != null){
            newTransaction.setSteamPayment(new SteamPayment(transaction.getSteamPayment().getLogin()));
            newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
        }else if(transaction.getPhoneNumberPayment() != null && transaction.getSteamPayment() != null && transaction.getToAnotherClientPayment() == null){
            newTransaction.setToAnotherClientPayment(new ToAnotherClientPayment(transaction.getToAnotherClientPayment().getPhoneNumber()));
            if(personRepository.existsPersonByPhoneNumber(transaction.getPhoneNumberPayment().getPhoneNumber())){
                newTransaction.setTransactionStatus(TransactionStatus.DECLINED);
            }else{
                Person person = personRepository.findPersonByPhoneNumber(transaction.getPhoneNumberPayment().getPhoneNumber());
                if(person.getCustomer() == null){
                    throw new ResourceNotFoundException("Customer not found, create a customer first");
                }
                Account account = person.getCustomer().getAccountList()
                        .stream().filter(account1 -> account1.getAccountType() == AccountType.MAIN)
                        .findFirst()
                        .orElse(accountService.createAccount(person.getCustomer().getCustomerId()));
                if(account.getAccountStatus() == AccountStatus.INACTIVE) {
                    throw new InvalidRequestException("the another client account is inactive");
                }
                account.setCurrentBalance(account.getCurrentBalance().add(transaction.getAmount()));
                accountRepository.save(account);
                newTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
            }
        }else{
            throw new InvalidRequestException("invalid request");
        }
        return newTransaction;
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::getTransactionDto)
                .collect(Collectors.toList());
    }

    public TransactionDto getTransactionDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDto.class);
    }
}
