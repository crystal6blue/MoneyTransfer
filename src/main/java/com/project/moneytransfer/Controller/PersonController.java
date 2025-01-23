package com.project.moneytransfer.Controller;


import com.project.moneytransfer.Dto.PersonDto;
import com.project.moneytransfer.Request.RequestAddNewPerson;
import com.project.moneytransfer.Request.RequestSetAddressToPerson;
import com.project.moneytransfer.Request.RequestSetEmailToPerson;
import com.project.moneytransfer.Response.ApiResponse;
import com.project.moneytransfer.Service.PersonService.IPersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/person")
@RequiredArgsConstructor
@Slf4j
public class PersonController {
    private final IPersonService personService;
    private final ModelMapper modelMapper;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createPerson(@Valid @RequestBody RequestAddNewPerson person) {
        personService.addNewPerson(person);

        return ResponseEntity.status(CREATED)
            .body(new ApiResponse("Person successfully inserted", null));
    }

    @GetMapping("/{personId}/get")
    public ResponseEntity<ApiResponse> getPersonById(@PathVariable Long personId) {
        PersonDto person = personService.getPersonById(personId);

        return ResponseEntity.ok()
            .body(new ApiResponse("Person successfully retrieved", person));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllPersons() {
        List<PersonDto> persons = personService.findAllPersons();

        if (persons.isEmpty()) {
            return ResponseEntity.status(NOT_FOUND)
                .body(new ApiResponse("No person found", null));
        }

        return ResponseEntity.status(OK)
            .body(new ApiResponse("Persons successfully retrieved", persons));
    }

    @GetMapping("/{phoneNumber}/findByPhoneNumber")
    public ResponseEntity<ApiResponse> findPersonByPhoneNumber(@PathVariable String phoneNumber) {
        PersonDto personDto = modelMapper.map(personService.findPersonByPhoneNumber(phoneNumber), PersonDto.class);

        return ResponseEntity.status(OK)
            .body(new ApiResponse("Person successfully retrieved using PhoneNumber", personDto));
    }

    @PutMapping("/{personId}/setEmail")
    public ResponseEntity<ApiResponse> setEmail(@Valid @RequestBody RequestSetEmailToPerson email, @PathVariable Long personId) {
        personService.setEmail(personId, email);

        return ResponseEntity.status(OK)
            .body(new ApiResponse("Person successfully updated(Email)", null));
    }

    @PutMapping("/{personId}/setAddress")
    public ResponseEntity<ApiResponse> setAddress(@Valid @RequestBody RequestSetAddressToPerson address, @PathVariable Long personId) {
        personService.setAddress(personId, address);

        return ResponseEntity.status(ACCEPTED)
            .body(new ApiResponse("Person successfully updated(Address)", null));
    }

    @DeleteMapping("/deletePerson")
    public ResponseEntity<ApiResponse> deletePersonById(@RequestParam Long personId) {
        personService.deletePerson(personId);

        return ResponseEntity.status(OK)
            .body(new ApiResponse("Person successfully deleted", null));
    }

    @PutMapping("/{personId}/setImage")
    public ResponseEntity<ApiResponse> setImage(@PathVariable Long personId, @RequestBody MultipartFile image) {
        personService.setImage(personId, image);

        return ResponseEntity.status(ACCEPTED)
            .body(new ApiResponse("Person's picture successfully updated", null));
    }

    @DeleteMapping("/deleteImage")
    public ResponseEntity<ApiResponse> deleteImage(@RequestParam Long personId) {
        personService.deleteImage(personId);

        return ResponseEntity.status(ACCEPTED)
            .body(new ApiResponse("Person's picture successfully deleted", null));
    }

    @GetMapping("/{personId}/getImageResource")
    public ResponseEntity<Resource> getImageResource(@PathVariable Long personId) throws SQLException {
        ByteArrayResource resource = personService.getImageResource(personId);

        return ResponseEntity.status(OK)
            .contentType(MediaType.IMAGE_JPEG)
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }


}










