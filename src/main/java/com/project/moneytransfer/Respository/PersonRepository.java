package com.project.moneytransfer.Respository;

import com.project.moneytransfer.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    void deletePersonByPhoneNumber(String phoneNumber);

    Person findPersonByPhoneNumber(String phoneNumber);
}
