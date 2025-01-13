package com.project.moneytransfer.Models;

import com.project.moneytransfer.Enums.CustomerType;
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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long CustomerId;
    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    private CustomerType CustomerType;
}
