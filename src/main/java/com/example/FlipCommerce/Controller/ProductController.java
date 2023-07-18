package com.example.FlipCommerce.Controller;

import com.example.FlipCommerce.DTO.Requestdto.ProductRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.ProductResponseDto;
import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.Service.ProductService;
import com.example.FlipCommerce.exception.SellerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
   @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

      try {
          ProductResponseDto productResponseDto=productService.addProduct(productRequestDto);
          return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);

      } catch (SellerNotFoundException e) {
         return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
      }
    }
    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getAllProductByCategoryAndPrice(@PathVariable("category") Category category ,@PathVariable("price") int price){
        List<ProductResponseDto> productResponseDtos= productService.getAllProductsByCategoryAndPrice(category, price);
        return new ResponseEntity(productResponseDtos,HttpStatus.FOUND);

    }
}
