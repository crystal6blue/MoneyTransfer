package com.project.moneytransfer.Respository;

import com.project.moneytransfer.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    void deletePersonByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT * FROM person p WHERE p.phone_number = ?1", nativeQuery = true)
    Person findPersonByPhoneNumber(String phoneNumber);

    Boolean existsPersonByPhoneNumber(String phoneNumber);
}
