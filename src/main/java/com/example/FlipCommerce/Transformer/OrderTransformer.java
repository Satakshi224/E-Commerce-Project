package com.example.FlipCommerce.Transformer;

import com.example.FlipCommerce.DTO.Requestdto.OrderRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.ItemResponseDto;
import com.example.FlipCommerce.DTO.Responsedto.OrderResponseDto;
import com.example.FlipCommerce.Model.Customer;
import com.example.FlipCommerce.Model.Item;
import com.example.FlipCommerce.Model.OrderEntity;
import jakarta.persistence.criteria.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {

    public static OrderEntity OrderRequestDtoToOrder( Item item,Customer customer){
        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }
    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity orderEntity){
        List<ItemResponseDto>itemResponseDtoList=new ArrayList<>();
        for(Item item: orderEntity.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }
        return  OrderResponseDto.builder()
                .orderDate(orderEntity.getOrderDate())
                .cardUsed(orderEntity.getCardUsed())
                .customerName(orderEntity.getCustomer().getName())
                .totalValue(orderEntity.getTotalValue())
                .orderNo(orderEntity.getOrderNo())
                .items(itemResponseDtoList)

                .build();
    }
}
