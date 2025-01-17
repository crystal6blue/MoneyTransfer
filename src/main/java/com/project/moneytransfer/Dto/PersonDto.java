package com.project.moneytransfer.Dto;

import com.project.moneytransfer.Enums.Gender;

import java.time.LocalDate;

public class PersonDto {
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

    // Current residential address
    private String address;
}
