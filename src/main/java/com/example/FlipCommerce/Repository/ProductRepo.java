package com.example.FlipCommerce.Repository;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Product> findByCategoryAndPrice(Category category, int price);



}
