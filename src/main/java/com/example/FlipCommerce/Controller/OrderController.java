package com.example.FlipCommerce.Controller;

import com.example.FlipCommerce.DTO.Requestdto.OrderRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.OrderResponseDto;
import com.example.FlipCommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")

public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
     try{
         OrderResponseDto orderResponseDto=orderService.placeOrder(orderRequestDto);
         return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);

     }
     catch (Exception e){
         return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
     }
    }
}
