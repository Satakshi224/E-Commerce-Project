package com.example.FlipCommerce.DTO.Requestdto;

import com.example.FlipCommerce.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    String sellerEmailId;
    Integer  price;
    String name;
    Category category;
    Integer quantity;
}
