package com.example.FlipCommerce.Service;

import com.example.FlipCommerce.DTO.Requestdto.ProductRequestDto;
import com.example.FlipCommerce.DTO.Responsedto.ProductResponseDto;
import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.Model.Product;
import com.example.FlipCommerce.Model.Seller;
import com.example.FlipCommerce.Repository.ProductRepo;
import com.example.FlipCommerce.Repository.SellerRepo;
import com.example.FlipCommerce.Transformer.ProductTransformer;
import com.example.FlipCommerce.exception.SellerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
     @Autowired
    ProductRepo productRepo;
     @Autowired
    SellerRepo sellerRepo;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
     Seller seller= sellerRepo.findByEmailId(productRequestDto.getSellerEmailId());
     if(seller==null) {
         throw new SellerNotFoundException("EmailId is not registered");

     }
     //dto to entity
        Product product= ProductTransformer.ProductReqDtoToProduct(productRequestDto);
        seller.getProducts().add(product);
        product.setSeller(seller);

        Seller savedSeller= sellerRepo.save(seller);//save both seller and product
        Product product1=savedSeller.getProducts().get(savedSeller.getProducts().size()-1);

        //prepare the responseDto
       return ProductTransformer.ProductToProductResponseDto(product1);

    }
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price){
         List<Product> products=productRepo.findByCategoryAndPrice(category, price);

         List<ProductResponseDto> productResponseDtos=new ArrayList<>();
         for(Product product: products){
             productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
         }
         return  productResponseDtos;
    }
}
