package com.project.moneytransfer.Models;

import com.project.moneytransfer.Models.PaymentInterfice.Payments;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToAnotherClientPayment implements Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long paymentId;

    @Column(name = "phone_number", updatable = false)
    private String phoneNumber;
}
