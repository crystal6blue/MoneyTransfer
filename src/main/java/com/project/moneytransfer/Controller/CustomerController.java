package com.project.moneytransfer.Controller;


import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.CustomerDto;
import com.project.moneytransfer.Response.ApiResponse;
import com.project.moneytransfer.Service.CustomerService.ICustomerService;
import lombok.RequiredArgsConstructor;
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
            CustomerDto customer= customerService.getCustomer(customerId);
            return ResponseEntity.ok().body(new ApiResponse("The customer successfully retrieved", customer));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getCustomers() {
            List<CustomerDto> customers= customerService.getCustomers();
            if(customers.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No data found", null));
            }
            return ResponseEntity.ok().body(new ApiResponse("All customers retrieved successfully", customers));
    }

    @GetMapping("/{customerId}/getAllAccounts")
    public ResponseEntity<ApiResponse> getCustomerAccounts(@PathVariable Long customerId) {
            List<AccountDto> accounts = customerService.getAccounts(customerId);
            if(accounts.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No account found", null));
            }
            return ResponseEntity.ok().body(new ApiResponse("All the customer's accounts have been retrieved", accounts));
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<ApiResponse> addCustomer(@RequestParam Long personId) {
            customerService.addCustomer(personId);
            return ResponseEntity.status(CREATED).body(new ApiResponse("The customer with person id: " + personId + " successfully created", null));
    }

    @PutMapping("/blockCustomer")
    public ResponseEntity<ApiResponse> blockCustomer(@RequestParam Long customerId){
            customerService.blockCustomer(customerId);
            return ResponseEntity.status(OK).body(new ApiResponse("The customer has successfully blocked", null));
    }
}
