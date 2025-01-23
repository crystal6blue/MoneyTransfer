package com.project.moneytransfer.Request;

import jakarta.validation.constraints.NotBlank;

public record RequestSetAddressToPerson(@NotBlank(message = "Fill address in appropriate way") String address) {
}

