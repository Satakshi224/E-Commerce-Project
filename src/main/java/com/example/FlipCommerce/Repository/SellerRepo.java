package com.example.FlipCommerce.Repository;

import com.example.FlipCommerce.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SellerRepo extends JpaRepository<Seller,Integer> {

    Seller findByEmailId(String sellerEmailId);
}
