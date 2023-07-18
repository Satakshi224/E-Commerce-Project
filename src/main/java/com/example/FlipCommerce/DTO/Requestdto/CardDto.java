package com.example.FlipCommerce.DTO.Requestdto;

import com.example.FlipCommerce.Enum.CardType;

import lombok.*;
import lombok.experimental.FieldDefaults;


import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardDto {
    String emailId;

    String cardNo;
    int cvv;

    CardType cardType;
    Date ValidTill;
}
