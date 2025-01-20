package com.project.moneytransfer.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.moneytransfer.Enums.TransactionStatus;
import com.project.moneytransfer.Models.PhoneNumberPayment;
import com.project.moneytransfer.Models.SteamPayment;
import com.project.moneytransfer.Models.ToAnotherClientPayment;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private Long transactionId;

    private TransactionStatus transactionStatus;

    private BigDecimal Amount;

    private LocalDateTime transactionDate;

    private PhoneNumberPayment phoneNumberPayment;

    private SteamPayment steamPayment;

    private ToAnotherClientPayment toAnotherClientPayment;
}
