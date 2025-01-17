package com.project.moneytransfer.Service.Transaction;

import com.project.moneytransfer.Enums.TransactionStatus;
import com.project.moneytransfer.Models.Transaction;
import com.project.moneytransfer.Request.RequestNewTransaction;

import java.util.List;

public interface ITransactionService {
    Transaction getTransactionById(Long transactionId);

    void createTransaction(RequestNewTransaction transaction, TransactionStatus status);

    List<Transaction> getAllTransactions();
}
