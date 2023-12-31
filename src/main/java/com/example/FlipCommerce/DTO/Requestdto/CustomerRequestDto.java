package com.example.FlipCommerce.DTO.Requestdto;

import com.example.FlipCommerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {
    String name;

    String emailId;


    String mobNo;


    Gender gender;
}
