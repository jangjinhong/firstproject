package com.example.firstproject.dto;

import com.example.firstproject.entity.Coffee;
import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CoffeeForm {
    private Long id;
    private String name;
    private String price;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
