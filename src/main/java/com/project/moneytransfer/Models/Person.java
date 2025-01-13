package com.project.moneytransfer.Models;

import com.project.moneytransfer.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Blob;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long PersonID;
    @Column(name = "last_name")
    private String LastName;
    @Column(name = "first_name")
    private String FirstName;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender Gender;
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate DateOfBirth;
    @Column(name = "email")
    @NaturalId
    private String Email;
    @Column(name = "phone_number")
    @NaturalId
    private String PhoneNumber;
    @Column(name = "address")
    private String Address;
    @Column(name = "picture_of_user")
    @Lob
    private Blob image;
}
