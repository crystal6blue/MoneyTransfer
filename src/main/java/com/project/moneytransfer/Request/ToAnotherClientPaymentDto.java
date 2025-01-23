package com.project.moneytransfer.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ToAnotherClientPaymentDto {
    @NotBlank(message = "Fill the phone number")
    private String phoneNumber;
}
