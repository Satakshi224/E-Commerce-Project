package com.example.FlipCommerce.Transformer;

import com.example.FlipCommerce.DTO.Responsedto.CartResponseDto;
import com.example.FlipCommerce.DTO.Responsedto.ItemResponseDto;
import com.example.FlipCommerce.Model.Cart;
import com.example.FlipCommerce.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart savedCart) {
        List<ItemResponseDto>itemResponseDtos=new ArrayList<>();
        for(Item item:savedCart.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item));
        }
       return   CartResponseDto.builder()
                .cartTotal(savedCart.getCartTotal())
                .customerName(savedCart.getCustomer().getName())
                .items(itemResponseDtos)
                .build();
    }
}
