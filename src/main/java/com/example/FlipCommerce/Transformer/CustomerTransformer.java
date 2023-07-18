package com.example.FlipCommerce.Transformer;

import com.example.FlipCommerce.DTO.Requestdto.CustomerRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.CustomerResponseDto;
import com.example.FlipCommerce.Model.Customer;

import java.util.ArrayList;

public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer( CustomerRequestDto customerRequestDto){
       return   Customer.builder()
               .name(customerRequestDto.getName())
               .emailId(customerRequestDto.getEmailId())
               .mobNo(customerRequestDto.getMobNo())
               .gender(customerRequestDto.getGender())
               .orders(new ArrayList<>())

               .build();
    }
    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
       return   CustomerResponseDto.builder()
                .name(customer.getName())
                .mobNo(customer.getMobNo())
                .emailId(customer.getEmailId())

                .build();
    }
}

