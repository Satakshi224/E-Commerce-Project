package com.example.FlipCommerce.Service;

import com.example.FlipCommerce.DTO.Requestdto.CardDto;
import com.example.FlipCommerce.DTO.Responsedto.CardResponseDto;
import com.example.FlipCommerce.Model.Card;
import com.example.FlipCommerce.Model.Customer;
import com.example.FlipCommerce.Repository.CustomerRepo;
import com.example.FlipCommerce.Transformer.CardTransformer;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CustomerRepo customerRepo;

    public CardResponseDto addCard(CardDto cardDto) throws CustomerNotFoundException {
      Customer customer= customerRepo.findByEmailId(cardDto.getEmailId());
      if(customer==null){
          throw new CustomerNotFoundException("Invalid email id");

      }
      //dto-->entity
        Card card= CardTransformer.CardDtoToCard(cardDto);
        card.setCustomer(customer);
        customer.getCards().add(card);
        Customer savedCustomer= customerRepo.save(customer);
           return CardTransformer.CardToCardResponseDto(card);
    }

}
