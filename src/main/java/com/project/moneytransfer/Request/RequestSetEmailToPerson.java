package com.project.moneytransfer.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record RequestSetEmailToPerson(@Email @NotBlank(message = "Fill email in appropriate way") String email) {
}
