package com.project.moneytransfer.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Steam payment table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public SteamPayment(@NotBlank String login) {
        this.login = login;
    }
}
