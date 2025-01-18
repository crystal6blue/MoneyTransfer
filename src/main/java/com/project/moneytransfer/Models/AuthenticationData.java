package com.project.moneytransfer.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;


// Authentication data for a Person
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class AuthenticationData {
    // Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authenticationId;

    // Phone number always must be unique
    @Column(name = "phone_number", unique = true)
    @NaturalId
    @NonNull
    private String phoneNumber;

    // Password for user
    @Column(name = "password")
    @NonNull
    private String password;

    // One Person can have only one authentication data and vice versa
    @OneToOne(mappedBy = "authenticationData")
    private Person person;
}
