package com.project.moneytransfer.Controller;


import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.CustomerDto;
import com.project.moneytransfer.Exceptions.AlreadyExistsException;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Response.ApiResponse;
import com.project.moneytransfer.Service.CustomerService.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;


    @GetMapping("/{customerId}/get")
    public ResponseEntity<ApiResponse> getCustomer(@PathVariable Long customerId) {
        try {
            CustomerDto customer= customerService.getCustomer(customerId);
            return ResponseEntity.ok().body(new ApiResponse("Success", customer));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getCustomers() {
        try {
            List<CustomerDto> customers= customerService.getCustomers();
            if(customers.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No customer found", NOT_FOUND));
            }
            return ResponseEntity.ok().body(new ApiResponse("Success", customers));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/{customerId}/getAllAccounts")
    public ResponseEntity<ApiResponse> getCustomerAccounts(@PathVariable Long customerId) {
        try {
            List<AccountDto> accounts = customerService.getAccounts(customerId);
            if(accounts.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No account found", NOT_FOUND));
            }
            return ResponseEntity.ok().body(new ApiResponse("Success", accounts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<ApiResponse> addCustomer(@RequestParam Long personId){
        try {
            customerService.addCustomer(personId);
            return ResponseEntity.status(CREATED).body(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), NOT_FOUND));
        } catch (AlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/blockCustomer")
    public ResponseEntity<ApiResponse> blockCustomer(@RequestParam Long customerId){
        try {
            customerService.blockCustomer(customerId);
            return ResponseEntity.status(OK).body(new ApiResponse("Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), INTERNAL_SERVER_ERROR));
        }
    }
}
