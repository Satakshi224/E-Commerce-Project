package com.example.FlipCommerce.Transformer;

import com.example.FlipCommerce.DTO.Requestdto.ProductRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.ProductResponseDto;
import com.example.FlipCommerce.Enum.ProductStatus;
import com.example.FlipCommerce.Model.Product;

public class ProductTransformer {

    public  static Product ProductReqDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .price(productRequestDto.getPrice())
                .name(productRequestDto.getName())
                .quantity(productRequestDto.getQuantity())
                .category(productRequestDto.getCategory())
                .productStatus(ProductStatus.AVAILABLE)

                .build();
    }
    public static ProductResponseDto ProductToProductResponseDto(Product product){
         return ProductResponseDto.builder()
                 .ProductName(product.getName())
                 .price(product.getPrice())
                 .category(product.getCategory())
                 .sellerName(product.getSeller().getName())
                 .productStatus(product.getProductStatus())
                 .build();
    }
}
