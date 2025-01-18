package com.project.moneytransfer.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.moneytransfer.Enums.TransactionStatus;
import com.project.moneytransfer.Models.PhoneNumberPayment;
import com.project.moneytransfer.Models.SteamPayment;
import com.project.moneytransfer.Models.ToAnotherClientPayment;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private Long transactionId;

    private TransactionStatus transactionStatus;

    private BigDecimal Amount;

    private LocalDateTime transactionDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PhoneNumberPayment phoneNumberPayment;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SteamPayment steamPayment;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ToAnotherClientPayment toAnotherClientPayment;
}
