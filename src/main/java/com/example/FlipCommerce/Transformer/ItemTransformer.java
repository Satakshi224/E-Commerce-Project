package com.example.FlipCommerce.Transformer;

import com.example.FlipCommerce.DTO.Responsedto.ItemResponseDto;
import com.example.FlipCommerce.Model.Customer;
import com.example.FlipCommerce.Model.Item;
import com.example.FlipCommerce.Model.Product;

public class ItemTransformer {
    public static Item ItemRequestDtoToItem( int requiredQuantity) {
        return  Item.builder()
                .requiredQuantity(requiredQuantity)


                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item) {
        return  ItemResponseDto.builder()
                .quantity(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();
    }
}
