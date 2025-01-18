package com.project.moneytransfer.Service.CustomerService;

import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.CustomerDto;

import com.project.moneytransfer.Enums.AccountStatus;
import com.project.moneytransfer.Enums.CustomerStatus;
import com.project.moneytransfer.Exceptions.ResourceNotFoundException;
import com.project.moneytransfer.Models.Account;
import com.project.moneytransfer.Models.Customer;
import com.project.moneytransfer.Models.Person;
import com.project.moneytransfer.Respository.CustomerRepository;
import com.project.moneytransfer.Respository.PersonRepository;
import com.project.moneytransfer.Service.PersonService.PersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final PersonRepository personRepository;
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDto getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .map(this::getCustomerDto)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    @Override
    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll()
                .stream().map(this::getCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addCustomer(Long personId) {
        Person person = personService.findPersonById(personId);
        Customer customer = new Customer();
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        person.setCustomer(customer);
        personRepository.save(person);
    }

    @Override
    public void blockCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        customer.setCustomerStatus(CustomerStatus.BLOCKED);
        customer.getAccountList().forEach(account -> account.setAccountStatus(AccountStatus.INACTIVE));
        customerRepository.save(customer);
    }

    @Override
    public List<AccountDto> getAccounts(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> customer.getAccountList()
                        .stream()
                        .map(this::getAccountDto)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerId + " not found"));
    }

    private CustomerDto getCustomerDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private AccountDto getAccountDto(Account account) {
        return modelMapper.map(account, AccountDto.class);
    }
}
