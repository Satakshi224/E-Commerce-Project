package com.example.FlipCommerce.Service;

import com.example.FlipCommerce.DTO.Requestdto.ItemRequestDto;
import com.example.FlipCommerce.Model.Customer;
import com.example.FlipCommerce.Model.Item;
import com.example.FlipCommerce.Model.Product;
import com.example.FlipCommerce.Repository.CustomerRepo;
import com.example.FlipCommerce.Repository.ProductRepo;
import com.example.FlipCommerce.Transformer.ItemTransformer;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import com.example.FlipCommerce.exception.InsufficientQuantityException;
import com.example.FlipCommerce.exception.OutOfStockException;
import com.example.FlipCommerce.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ItemService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CustomerRepo customerRepo;
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {
        Optional<Product>productOptional=productRepo.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw  new ProductNotFoundException("Product is not available");

        }
        Customer customer=customerRepo.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");

        }
        Product product=productOptional.get();
        if(product.getQuantity()==0) {
            throw new OutOfStockException("Product is out of stock");
        }
        if(product.getQuantity()< itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! the required quantity is not available");
        }
       Item item= ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());


        return item;
    }
}
