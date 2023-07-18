package com.example.FlipCommerce.Repository;

import com.example.FlipCommerce.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findByEmailId(String emailId);
}
