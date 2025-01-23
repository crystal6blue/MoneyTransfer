package com.project.moneytransfer.Service.Transaction;

import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Request.RequestNewTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    TransactionDto getTransactionById(Long transactionId);

    TransactionDto createTransaction(RequestNewTransaction transaction, Long accountId);

    List<TransactionDto> getAllTransactions();

    void transferMoneyBetweenAccounts(Long accountId_1, Long accountId_2, BigDecimal amount);
}
