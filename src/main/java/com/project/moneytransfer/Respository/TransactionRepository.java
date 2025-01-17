package com.project.moneytransfer.Respository;

import com.project.moneytransfer.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
