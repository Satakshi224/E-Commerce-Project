package com.example.FlipCommerce.Service;


import com.example.FlipCommerce.DTO.Requestdto.CustomerRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.CustomerResponseDto;
import com.example.FlipCommerce.Model.Cart;
import com.example.FlipCommerce.Model.Customer;
import com.example.FlipCommerce.Repository.CustomerRepo;
import com.example.FlipCommerce.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto){
        Customer customer= CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart=Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();
        customer.setCart(cart);
        Customer savedCustomer= customerRepo.save(customer);
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

    }
}
