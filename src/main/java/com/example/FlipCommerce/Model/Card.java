package com.example.FlipCommerce.Model;


import com.example.FlipCommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
@Builder

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true,nullable = false)
    String cardNo;
    int cvv;
    @Enumerated(EnumType.STRING)
    CardType cardType;
    Date ValidTill;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
