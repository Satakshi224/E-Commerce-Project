package com.example.FlipCommerce.DTO.Requestdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {

    String emailId;
    int productId;

    String cardNo;
    int cvv;
    int requiredQuantity;

}
