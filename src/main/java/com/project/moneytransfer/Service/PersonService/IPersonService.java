package com.project.moneytransfer.Service.PersonService;

import com.project.moneytransfer.Dto.PersonDto;
import com.project.moneytransfer.Models.Person;
import com.project.moneytransfer.Request.RequestAddNewPerson;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPersonService {
    void addNewPerson(RequestAddNewPerson person);

    PersonDto getPersonById(Long personId);

    Person findPersonByPhoneNumber(String phoneNumber);

    void setEmail(Long personId, String email);

    void setAddress(Long personId, String address);

    List<PersonDto> findAllPersons();

    void deletePerson(Long personId);

    void setImage(Long personId, MultipartFile image);

    void deleteImage(Long personId);

    Resource getImageResource(Long personId);
}
