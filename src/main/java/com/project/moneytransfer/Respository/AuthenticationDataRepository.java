package com.project.moneytransfer.Respository;

import com.project.moneytransfer.Models.AuthenticationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDataRepository extends JpaRepository<AuthenticationData, Long> {
}
