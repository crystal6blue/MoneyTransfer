package com.project.moneytransfer.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.moneytransfer.Enums.Gender;
import com.project.moneytransfer.Models.AuthenticationData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestAddNewPerson {
    // Last name
    @NotBlank
    private String lastName;

    // First name
    @NotBlank
    private String firstName;

    // Gender enum
    // Male, Female
    @NotNull
    private Gender gender;

    // Date of Birth
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    // Phone number
    // Every person has their unique phone number
    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
            message = "Invalid phone number format"
    )
    private String phoneNumber;

    // Picture of a person
    // Every person has its own single authentication data
    @NotNull
    @Valid
    private AuthenticationData authenticationData;
}
