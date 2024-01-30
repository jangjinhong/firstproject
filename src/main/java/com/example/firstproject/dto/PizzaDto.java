package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDto {
    private Long id;
    private String name;
    private int price;

    public static PizzaDto createPizzaDto(Pizza pizza) {
        return new PizzaDto(pizza.getId(), pizza.getName(), pizza.getPrice());
    }
}
