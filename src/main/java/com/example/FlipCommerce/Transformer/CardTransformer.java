package com.example.FlipCommerce.Transformer;

import com.example.FlipCommerce.DTO.Requestdto.CardDto;
import com.example.FlipCommerce.DTO.Responsedto.CardResponseDto;
import com.example.FlipCommerce.Model.Card;

public class CardTransformer {


    public static CardResponseDto CardToCardResponseDto(Card card){
        return  CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .CustomerName(card.getCustomer().getName())
                .build();
    }

    public  static Card CardDtoToCard(CardDto cardDto ){
        return Card.builder()
                .cardNo(cardDto.getCardNo())
                .cvv(cardDto.getCvv())
                .cardType(cardDto.getCardType())
                .ValidTill(cardDto.getValidTill())

                .build();
    }

}
