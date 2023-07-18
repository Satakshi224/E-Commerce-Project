package com.example.FlipCommerce.Service;


import com.example.FlipCommerce.DTO.Requestdto.OrderRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.OrderResponseDto;
import com.example.FlipCommerce.Enum.ProductStatus;
import com.example.FlipCommerce.Model.*;
import com.example.FlipCommerce.Repository.CardRepo;
import com.example.FlipCommerce.Repository.CustomerRepo;
import com.example.FlipCommerce.Repository.OrderRepository;
import com.example.FlipCommerce.Repository.ProductRepo;
import com.example.FlipCommerce.Transformer.ItemTransformer;
import com.example.FlipCommerce.Transformer.OrderTransformer;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import com.example.FlipCommerce.exception.InsufficientQuantityException;
import com.example.FlipCommerce.exception.InvalidCardException;
import com.example.FlipCommerce.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class  OrderService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    OrderRepository orderRepo;
    @Autowired
    CardRepo cardRepo;
    @Autowired
    ProductRepo productRepo;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException {
        Customer customer=customerRepo.findByEmailId(orderRequestDto.getEmailId());
       if(customer==null){
           throw new CustomerNotFoundException("Customer doesn't exist");
     }
        Optional<Product> optionalProduct=productRepo.findById(orderRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Sorry! Product doesn't exist");
        }
        Product product=optionalProduct.get();
        if(product.getQuantity()< orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry the required quantity is not available");
        }
        Card card=cardRepo.findByCardNo(orderRequestDto.getCardNo());
        Date date=new Date();
        if(card==null || card.getCvv()!= orderRequestDto.getCvv() || date.after(card.getValidTill())){

            throw new InvalidCardException("Sorry! You can't use this card");

        }
        int newQuantity= product.getQuantity()-orderRequestDto.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        // create item
        Item item= ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);


        //create order
        OrderEntity orderEntity= OrderTransformer.OrderRequestDtoToOrder(item,customer);
        String maskedCard=generateMaskedCardNo(card);
        orderEntity.setCardUsed(maskedCard);
        orderEntity.getItems().add(item); //

        item.setOrderEntity(orderEntity);

      OrderEntity orderEntity1= orderRepo.save(orderEntity);
      customer.getOrders().add(orderEntity1);
      product.getItems().add(orderEntity1.getItems().get(0));

      //prepare responseDto
     return OrderTransformer.OrderToOrderResponseDto(orderEntity1);
    }
    public OrderEntity placeOrder(Cart cart,Card card) throws InsufficientQuantityException {
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(generateMaskedCardNo(card));
        int totalValue=0;
        for(Item item: cart.getItems()) {
            Product product = item.getProduct();
            if (item.getRequiredQuantity() > product.getQuantity()) {
                throw new InsufficientQuantityException("Sorry the Required Quantity not present!!");

            }
            totalValue += item.getRequiredQuantity() * product.getPrice();
            int newQuantity = product.getQuantity() - item.getRequiredQuantity();
            product.setQuantity(newQuantity);
            if (newQuantity == 0) {
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            item.setOrderEntity(orderEntity);
        }
            orderEntity.setTotalValue(totalValue);
            orderEntity.setItems(cart.getItems());
            orderEntity.setCustomer(cart.getCustomer());


   return orderEntity;
    }

    private String  generateMaskedCardNo(Card card){
    String ans="";
    String originalCardNo=card.getCardNo();
    for(int i=0;i<originalCardNo.length()-4;i++){
        ans+="X";
    }
   ans+=originalCardNo.substring(originalCardNo.length()-4);
    return ans;
    }
}
