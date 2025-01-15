package com.project.moneytransfer.Models;

import com.project.moneytransfer.Enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
import java.time.LocalDate;

// Person table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Person {
    // The ID of a person
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long personId;

    // Last name
    @Column(name = "last_name")
    private String lastName;

    // First name
    @Column(name = "first_name")
    private String firstName;

    // Gender
    // Male, Female
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // Date of Birth
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    // Email
    // Every person has their unique email
    @Column(name = "email", unique = true)
    @NaturalId
    private String email;

    // Phone number
    // Every person has their unique phone number
    @Column(name = "phone_number", unique = true)
    @NaturalId
    private String phoneNumber;

    // Current residential address
    @Column(name = "address")
    private String address;

    // Picture of a person
    // It will be stored in database
    // as binary type
    @Column(name = "picture_of_user")
    @Lob
    private Blob image;

    // Every person has its own single authentication data
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "authentication_data_id")
    private AuthenticationData authenticationData;

    // Person can be customer(Only one)
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;
}
