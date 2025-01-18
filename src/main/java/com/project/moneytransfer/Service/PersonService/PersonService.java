package com.project.moneytransfer.Service.PersonService;

import com.project.moneytransfer.Dto.PersonDto;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Models.Person;
import com.project.moneytransfer.Request.RequestAddNewPerson;
import com.project.moneytransfer.Respository.PersonRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addNewPerson(RequestAddNewPerson person) {
        Person newPerson = createPersonRequest(person);
        personRepository.save(newPerson);
    }

    private Person createPersonRequest(RequestAddNewPerson person){
        Person newPerson = new Person();
        newPerson.setLastName(person.getLastName());
        newPerson.setFirstName(person.getFirstName());
        newPerson.setDateOfBirth(person.getDateOfBirth());
        newPerson.setGender(person.getGender());
        newPerson.setPhoneNumber(person.getPhoneNumber());
        newPerson.setCustomer(person.getCustomer());
        newPerson.setAuthenticationData(person.getAuthenticationData());
        return newPerson;
    }

    @Override
    public PersonDto getPersonById(Long personId) {
        return modelMapper.map(findPersonById(personId), PersonDto.class);
    }

    public Person findPersonById(Long personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + "not found"));
    }

    @Override
    public Person findPersonByPhoneNumber(@Size(min = 10, max = 11) String phoneNumber) {
        return Optional.of(personRepository.findPersonByPhoneNumber(phoneNumber))
                .orElseThrow(() -> new ResourceNotFoundException("Such person doesn't exist"));
    }

    @Override
    public void setEmail(Long personId,@Email String email) {
        Person person = findPersonById(personId);
        person.setEmail(email);
        personRepository.save(person);
    }

    @Override
    public void setAddress(Long personId,@NotBlank String address) {
        Person person = findPersonById(personId);
        person.setAddress(address);
        personRepository.save(person);
    }

    @Override
    public List<PersonDto> findAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePerson(Long personId) {
        Person person = findPersonById(personId);
        personRepository.deletePersonByPhoneNumber(person.getPhoneNumber());
    }

    @Override
    public void setImage(Long personId, MultipartFile image) {
        Person person = findPersonById(personId);
        try {
            person.setImage(new SerialBlob(image.getBytes()));
            personRepository.save(person);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteImage(Long personId) {
        Person person = findPersonById(personId);
        personRepository.deletePersonByPhoneNumber(person.getPhoneNumber());
    }

    @Override
    public Resource getImageResource(Long personId) {
        Person person = findPersonById(personId);
        try {
            return new ByteArrayResource(person.getImage().getBytes(1, (int) person.getImage().length()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PersonDto convertToDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }
}
