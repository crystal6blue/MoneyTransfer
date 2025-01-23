package com.project.moneytransfer.Service.PersonService;

import com.project.moneytransfer.Dto.PersonDto;
import com.project.moneytransfer.Models.Person;
import com.project.moneytransfer.Request.RequestAddNewPerson;
import com.project.moneytransfer.Request.RequestSetAddressToPerson;
import com.project.moneytransfer.Request.RequestSetEmailToPerson;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

public interface IPersonService {
    void addNewPerson(RequestAddNewPerson person);

    PersonDto getPersonById(Long personId);

    Person findPersonByPhoneNumber(String phoneNumber);

    void setEmail(Long personId, RequestSetEmailToPerson email);

    void setAddress(Long personId, RequestSetAddressToPerson address);

    List<PersonDto> findAllPersons();

    void deletePerson(Long personId);

    void setImage(Long personId, MultipartFile image);

    void deleteImage(Long personId);

    ByteArrayResource getImageResource(Long personId) throws SQLException;
}
