package com.project.moneytransfer.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Phone number payment table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhoneNumberPayment {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    // Phone number
    // it won't be changed after initializing
    @Column(name = "phone_number", updatable = false)
    private String phoneNumber;
}
