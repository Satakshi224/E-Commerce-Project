package com.example.FlipCommerce.Model;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Builder

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    Integer  price;
     String name;
    @Enumerated(EnumType.STRING)
     Category category;
     Integer quantity;
     @Enumerated(EnumType.STRING)
     ProductStatus productStatus;

     @ManyToOne
     @JoinColumn
    Seller seller;

     @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item>items=new ArrayList<>();




}
