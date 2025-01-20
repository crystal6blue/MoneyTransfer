package com.project.moneytransfer.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.moneytransfer.Enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PersonDto {

    private Long personId;
    // Last name
    private String lastName;

    // First name
    private String firstName;

    // Gender
    // Male, Female
    private Gender gender;

    // Date of Birth
    private LocalDate dateOfBirth;

    // Phone number
    // Every person has their unique phone number
    private String phoneNumber;

    private String email;

    // Current residential address
    private String address;
}
