package com.project.moneytransfer.Service.Transaction;

import com.project.moneytransfer.Enums.TransactionStatus;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Models.Transaction;
import com.project.moneytransfer.Request.RequestNewTransaction;
import com.project.moneytransfer.Respository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(()->new ResourceNotFoundException("Transaction not found"));
    }

    @Override
    public void createTransaction(RequestNewTransaction transaction, TransactionStatus status) {
        Transaction newTransaction = getTransactionFromRequest(transaction, status);
        transactionRepository.save(newTransaction);
    }

    private Transaction getTransactionFromRequest(RequestNewTransaction transaction, TransactionStatus status) {
        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionStatus(transaction.getTransactionStatus());
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setAccount(transaction.getAccount());
        newTransaction.setTransactionStatus(status);
        return newTransaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
