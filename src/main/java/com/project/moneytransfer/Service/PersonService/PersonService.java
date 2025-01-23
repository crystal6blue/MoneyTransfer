package com.project.moneytransfer.Service.PersonService;

import com.project.moneytransfer.Dto.PersonDto;
import com.project.moneytransfer.Exceptions.InvalidRequestException;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Exceptions.SomethingHappenedToImageException;
import com.project.moneytransfer.Models.Person;
import com.project.moneytransfer.Request.RequestAddNewPerson;
import com.project.moneytransfer.Request.RequestSetAddressToPerson;
import com.project.moneytransfer.Request.RequestSetEmailToPerson;
import com.project.moneytransfer.Respository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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

        if(!person.getPhoneNumber().equals(person.getAuthenticationData().getPhoneNumber())){
            throw new InvalidRequestException("Person phone number does not match with authentication data");
        }

        return Person.builder()
                .lastName(person.getLastName())
                .firstName(person.getFirstName())
                .dateOfBirth(person.getDateOfBirth())
                .gender(person.getGender())
                .phoneNumber(person.getPhoneNumber())
                .authenticationData(person.getAuthenticationData())
                .build();
    }

    @Override
    public PersonDto getPersonById(Long personId) {

        return modelMapper.map(findPersonById(personId), PersonDto.class);
    }

    public Person findPersonById(Long personId) {

        return personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person with id " + personId + " not found"));
    }

    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {

        return Optional.ofNullable(personRepository.findPersonByPhoneNumber(phoneNumber))
                .orElseThrow(() -> new ResourceNotFoundException("Such person with phone number " + phoneNumber + " doesn't exist"));
    }

    @Override
    public void setEmail(Long personId, RequestSetEmailToPerson email) {

        Person person = findPersonById(personId);
        person.setEmail(email.email());
        personRepository.save(person);
    }

    @Override
    public void setAddress(Long personId, RequestSetAddressToPerson address) {

        Person person = findPersonById(personId);
        person.setAddress(address.address());
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
        personRepository.delete(person);
    }

    @Override
    public void setImage(Long personId, MultipartFile image) {

        Person person = findPersonById(personId);
        try {
            person.setImage(new SerialBlob(image.getBytes()));
            personRepository.save(person);
        } catch (SQLException | IOException e) {
            throw new SomethingHappenedToImageException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteImage(Long personId) {

        Person person = findPersonById(personId);

        if(person.getImage() == null){
            throw new ResourceNotFoundException("Image with person id " + personId + " not found");
        }

        person.setImage(null);
        personRepository.save(person);
    }

    @Transactional
    @Override
    public ByteArrayResource getImageResource(Long personId) {

        Person person = findPersonById(personId);

        if (person.getImage() == null) {
            throw new ResourceNotFoundException("No image found for person ID: " + personId);
        }

        Blob imageBlob = person.getImage();

        try {
            return new ByteArrayResource(imageBlob.getBytes(1, (int) person.getImage().length()));
        } catch (SQLException e) {
            throw new SomethingHappenedToImageException("Failed to fetch image data: : " + e.getMessage());
        }
    }

    private PersonDto convertToDto(Person person) {

        return modelMapper.map(person, PersonDto.class);
    }
}
