package com.example.FlipCommerce.Controller;

import com.example.FlipCommerce.DTO.Requestdto.CardDto;
import com.example.FlipCommerce.DTO.Responsedto.CardResponseDto;
import com.example.FlipCommerce.Service.CardService;
import com.example.FlipCommerce.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
  @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard (@RequestBody CardDto cardDto){
      try {
        CardResponseDto cardResponseDto=cardService.addCard(cardDto);
        return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);

      } catch (CustomerNotFoundException e) {
        return  new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);


      }

    }
}
