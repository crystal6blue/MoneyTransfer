package com.project.moneytransfer.Request;

import jakarta.validation.constraints.NotBlank;

public record RequestSetAddressToPerson(@NotBlank String address) {
}

