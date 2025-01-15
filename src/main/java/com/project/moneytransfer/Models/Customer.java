package com.project.moneytransfer.Models;

import com.project.moneytransfer.Enums.CustomerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// Customer table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    // the ID of a customer
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long customerId;

    // Enums type for Customer's type
    // It will be stored as a string type
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerType customerType;

    // One Person can have only one authentication data and vice versa
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Person person;

    //One customer can have many account(up to 3)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Size(min = 0, max = 3)
    private List<Account> accountList;
}
