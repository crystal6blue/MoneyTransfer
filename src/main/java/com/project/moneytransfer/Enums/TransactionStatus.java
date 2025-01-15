package com.project.moneytransfer.Enums;

// Transaction Status enum
// COMPLETED (when transaction has been successful)
// DECLINED (if the amount of money on account isn't enough for money that you were going to transfer)
// FAILED (it will be initialized when account is inactive or user's phone number is not found)
public enum TransactionStatus {
    COMPLETED,
    DECLINED,
    FAILED
}
