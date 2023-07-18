package com.example.FlipCommerce.Repository;

import com.example.FlipCommerce.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {

}
