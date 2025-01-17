package com.project.moneytransfer.Request;

import com.project.moneytransfer.Enums.Gender;
import com.project.moneytransfer.Models.AuthenticationData;
import com.project.moneytransfer.Models.Customer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestAddNewPerson {
    // Last name
    @NotEmpty
    private String lastName;

    // First name
    @NotEmpty
    private String firstName;

    // Gender
    // Male, Female
    @NotNull
    private Gender gender;

    // Date of Birth
    @NotNull
    private LocalDate dateOfBirth;

    // Phone number
    // Every person has their unique phone number
    @Size(min = 10, max = 11)
    private String phoneNumber;

    // Picture of a person
    // Every person has its own single authentication data
    @NotNull
    private AuthenticationData authenticationData;

    // Person can be customer(Only one)
    @NotNull
    private Customer customer;
}
