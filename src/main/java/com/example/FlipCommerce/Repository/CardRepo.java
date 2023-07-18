package com.example.FlipCommerce.Repository;

import com.example.FlipCommerce.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CardRepo extends JpaRepository<Card,Integer> {

    Card findByCardNo(String cardNo);
}
