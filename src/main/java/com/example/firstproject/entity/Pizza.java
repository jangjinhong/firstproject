package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor
public class Pizza {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    public static Pizza createPizza(PizzaDto dto) {
        return new Pizza(dto.getId(), dto.getName(), dto.getPrice());
    }

    public void patch(PizzaDto dto) {
        if(this.id != dto.getId())
            throw new IllegalArgumentException("제품 수정 실패! id가 잘못됐습니다.");

        if(dto.getName() != null)
            this.name = dto.getName();
        if(dto.getPrice() != 0)
            this.price = dto.getPrice();
    }
}
