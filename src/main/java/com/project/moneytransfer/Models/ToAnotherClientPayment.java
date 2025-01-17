package com.project.moneytransfer.Models;

import jakarta.persistence.*;
import lombok.*;

// To another client payment table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToAnotherClientPayment  {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long paymentId;

    // Phone number
    // it won't be changed after initializing
    @Column(name = "phone_number", updatable = false)
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
