package com.project.moneytransfer.Service.CustomerService;


import com.project.moneytransfer.Dto.AccountDto;
import com.project.moneytransfer.Dto.CustomerDto;

import java.util.List;

public interface ICustomerService {
    CustomerDto getCustomer(Long customerId);

    List<CustomerDto> getCustomers();

    void addCustomer(Long personId);

    void blockCustomer(Long customerId);

    List<AccountDto> getAccounts(Long customerId);
}
