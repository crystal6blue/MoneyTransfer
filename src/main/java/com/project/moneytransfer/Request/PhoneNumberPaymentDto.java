package com.project.moneytransfer.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PhoneNumberPaymentDto {
    @NotBlank
    private String phoneNumber;
}
