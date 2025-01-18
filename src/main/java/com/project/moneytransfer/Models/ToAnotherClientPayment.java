package com.project.moneytransfer.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public ToAnotherClientPayment(@NotBlank String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
