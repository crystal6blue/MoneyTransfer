package com.project.moneytransfer.Enums;

// Here's enums that defines payment types
// STEAM       (Actually it can provide genuine information about the login that had be used existed or not. Anyway It will accept it)
// ANOTHER_CLIENT  (If user doesn't exist in database then transaction status will be initialized declined)
// PHONE_NUMBER (Also, it won't give genuine information about phone number. It will accept it too as STEAM payment type does)
public enum PaymentTypes {
    STEAM,
    ANOTHER_CLIENT,
    PHONE_NUMBER
}
