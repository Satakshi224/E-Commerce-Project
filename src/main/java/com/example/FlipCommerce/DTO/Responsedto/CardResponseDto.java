package com.example.FlipCommerce.DTO.Responsedto;

import com.example.FlipCommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String CustomerName;


    String cardNo;
    CardType cardType;
}
