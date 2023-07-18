package com.example.FlipCommerce.Service;

import com.example.FlipCommerce.DTO.Requestdto.CheckoutCartRequestDto;
import com.example.FlipCommerce.DTO.Requestdto.ItemRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.CartResponseDto;
import com.example.FlipCommerce.DTO.Responsedto.OrderResponseDto;
import com.example.FlipCommerce.Model.*;
import com.example.FlipCommerce.Repository.*;
import com.example.FlipCommerce.Transformer.CartTransformer;
import com.example.FlipCommerce.Transformer.OrderTransformer;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import com.example.FlipCommerce.exception.EmptyCartException;
import com.example.FlipCommerce.exception.InsufficientQuantityException;
import com.example.FlipCommerce.exception.InvalidCardException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CardRepo cardRepo;
    @Autowired OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {
        Customer customer=customerRepo.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product=productRepo.findById(itemRequestDto.getProductId()).get();
    Cart cart=customer.getCart();
    cart.setCartTotal(cart.getCartTotal()+ item.getRequiredQuantity()* product.getPrice());
    cart.getItems().add(item);
    item.setCart(cart);
    item.setProduct(product);

    Cart savedCart=cartRepository.save(cart);
    Item savedItem=cart.getItems().get(cart.getItems().size()-1);
    product.getItems().add(savedItem);

        return CartTransformer.CartToCartResponseDto(savedCart);
    }
    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException {
       Customer customer=customerRepo.findByEmailId(checkoutCartRequestDto.getEmailId());
       if(customer==null){
           throw  new CustomerNotFoundException("Customer doesn't exists");
       }
        Card card=cardRepo.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date date=new Date();
        if(card==null || card.getCvv()!= checkoutCartRequestDto.getCvv() || date.after(card.getValidTill())){

            throw new InvalidCardException("Sorry! You can't use this card!");

        }
        Cart cart=customer.getCart();
        if(cart.getItems().size()==0){
            throw new EmptyCartException("Cart is empty!!");

        }
        try {
            OrderEntity order = orderService.placeOrder(cart, card) ;
            resetCart(cart);

            OrderEntity savedOrder=orderRepository.save(order);
            customer.getOrders().add(savedOrder);

            return OrderTransformer.OrderToOrderResponseDto(savedOrder);
        }
        catch (InsufficientQuantityException e){
            throw e;
        }
    }
    private void resetCart(Cart cart){
      cart.setCartTotal(0);
      for(Item item : cart.getItems()){
          item.setCart(null);
          cart.setItems(new ArrayList<>());
      }
    }
}
