package com.example.FlipCommerce.Service;

import com.example.FlipCommerce.DTO.Requestdto.SellerRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.SellerResponseDto;
import com.example.FlipCommerce.Model.Seller;
import com.example.FlipCommerce.Repository.SellerRepo;
import com.example.FlipCommerce.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepo sellerRepo;
    public  SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){
      Seller seller= SellerTransformer.SellerRequestToSeller(sellerRequestDto);
      Seller savedSeller=sellerRepo.save(seller);
      return SellerTransformer.SellerToSellerResponseDto(savedSeller);

    }
}
