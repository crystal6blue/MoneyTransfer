package com.project.moneytransfer.Respository;

import com.project.moneytransfer.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
