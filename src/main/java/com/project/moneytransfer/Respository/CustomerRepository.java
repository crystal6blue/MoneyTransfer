package com.project.moneytransfer.Respository;

import com.project.moneytransfer.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
