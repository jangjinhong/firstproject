package com.example.firstproject.service;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;
    public List<Pizza> index() {
        return (List<Pizza>) pizzaRepository.findAll();
    }

    public PizzaDto create(PizzaDto dto) {
        Pizza pizza = Pizza.createPizza(dto);
        Pizza created = pizzaRepository.save(pizza);
        return PizzaDto.createPizzaDto(created);
    }

    public Pizza show(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    @Transactional
    public PizzaDto update(Long id, PizzaDto dto) {
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정 실패! 해당 id가 없습니다."));
        target.patch(dto);
        Pizza updated = pizzaRepository.save(target);
        return PizzaDto.createPizzaDto(updated);
    }

    @Transactional
    public PizzaDto delete(Long id) {
        Pizza target = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제 실패! 해당 제품이 없습니다."));
        pizzaRepository.delete(target);
        return PizzaDto.createPizzaDto(target);
    }
}
