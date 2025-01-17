package com.project.moneytransfer.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Steam payment table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SteamPayment {
    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    // The Login of steam account
    // it won't be changed after initializing
    @Column(name = "login", updatable = false)
    private String login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
