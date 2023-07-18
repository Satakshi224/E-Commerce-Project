package com.example.FlipCommerce.Controller;


import com.example.FlipCommerce.DTO.Requestdto.CheckoutCartRequestDto;
import com.example.FlipCommerce.DTO.Requestdto.ItemRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.CartResponseDto;
import com.example.FlipCommerce.DTO.Responsedto.OrderResponseDto;
import com.example.FlipCommerce.Model.Item;
import com.example.FlipCommerce.Service.CartService;
import com.example.FlipCommerce.Service.ItemService;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import com.example.FlipCommerce.exception.InsufficientQuantityException;
import com.example.FlipCommerce.exception.OutOfStockException;
import com.example.FlipCommerce.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;
    @PostMapping("/add")
    public ResponseEntity  addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try{
            Item item=itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto= cartService.addToCart(item,itemRequestDto);
            return new ResponseEntity<>(cartResponseDto,HttpStatus.ACCEPTED);

        }
        catch (Exception exception){
            return  new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto){

        try{
            OrderResponseDto orderResponseDto=cartService.checkoutCart(checkoutCartRequestDto);
          return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
