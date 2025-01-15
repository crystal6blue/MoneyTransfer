package com.project.moneytransfer.Models;

import com.project.moneytransfer.Models.PaymentInterfice.Payments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PhoneNumberPayment implements Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "phone_number", updatable = false)
    private String phoneNumber;
}
