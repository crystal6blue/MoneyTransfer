package com.project.moneytransfer.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class AuthenticationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authenticationId;
    @Column(name = "phone_number", unique = true)
    private String PhoneNumber;
    @Column(name = "password")
    private String Password;
}
