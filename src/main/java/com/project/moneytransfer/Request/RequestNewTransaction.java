package com.project.moneytransfer.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class RequestNewTransaction {

    @NotNull
    private BigDecimal amount;

    private PhoneNumberPaymentDto phoneNumberPayment;

    private SteamPaymentDto steamPayment;

    private ToAnotherClientPaymentDto toAnotherClientPayment;
}
