package com.project.moneytransfer.Dto;

import com.project.moneytransfer.Enums.CustomerStatus;
import lombok.Data;

@Data
public class CustomerDto {
    private Long customerId;

    private CustomerStatus customerStatus;

    private PersonDto personDto;
}
