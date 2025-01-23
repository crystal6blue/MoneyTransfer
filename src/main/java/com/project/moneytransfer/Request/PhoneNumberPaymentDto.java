package com.project.moneytransfer.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PhoneNumberPaymentDto {
    @NotBlank(message = "There is something wrong with phone number")
    private String phoneNumber;
}
