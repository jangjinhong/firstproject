package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id 자동 증가
    private Long id;

    private String name;
    private String price;


    public void patch(Coffee coffee) {
        if(coffee.getName() != null)
            this.name = coffee.name;
        if(coffee.getPrice() != null)
            this.price = coffee.price;
    }
}
