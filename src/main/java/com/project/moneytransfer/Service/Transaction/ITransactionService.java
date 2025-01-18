package com.project.moneytransfer.Service.Transaction;

import com.project.moneytransfer.Dto.TransactionDto;
import com.project.moneytransfer.Request.RequestNewTransaction;

import java.util.List;

public interface ITransactionService {
    TransactionDto getTransactionById(Long transactionId);

    TransactionDto createTransaction(RequestNewTransaction transaction, Long accountId);

    List<TransactionDto> getAllTransactions();
}
