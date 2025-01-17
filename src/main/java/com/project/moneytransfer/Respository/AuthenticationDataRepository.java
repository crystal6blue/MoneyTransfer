package com.project.moneytransfer.Respository;

import com.project.moneytransfer.Models.AuthenticationData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationDataRepository extends JpaRepository<AuthenticationData, Long> {
}
