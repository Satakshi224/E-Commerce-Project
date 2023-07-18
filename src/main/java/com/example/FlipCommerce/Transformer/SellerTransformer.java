package com.example.FlipCommerce.Transformer;

import com.example.FlipCommerce.DTO.Requestdto.SellerRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.SellerResponseDto;
import com.example.FlipCommerce.Model.Seller;

public class SellerTransformer {
    public static Seller SellerRequestToSeller(SellerRequestDto sellerRequestDto){
       return Seller.builder()
               .name(sellerRequestDto.getName())
               .emailId(sellerRequestDto.getEmailId())
               .mobNo(sellerRequestDto.getMobNo())
               .build();
    }
    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
       return SellerResponseDto.builder()
               .name(seller.getName())
               .mobNo(seller.getMobNo())

               .build();
    }
}
