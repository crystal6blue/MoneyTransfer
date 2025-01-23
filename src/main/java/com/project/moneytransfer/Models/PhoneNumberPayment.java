package com.project.moneytransfer.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Phone number payment table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public PhoneNumberPayment(@Size(min = 10, max = 11) String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
