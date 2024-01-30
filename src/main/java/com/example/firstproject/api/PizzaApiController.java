package com.example.firstproject.api;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaApiController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/api/pizzas")
    public List<Pizza> index() {
        return pizzaService.index();
    }

    @GetMapping("/api/pizzas/{id}")
    public Pizza show(@PathVariable Long id) {
        return pizzaService.show(id);
    }

    @PostMapping("/api/pizzas")
    public ResponseEntity<PizzaDto> create(@RequestBody PizzaDto dto) {
        PizzaDto created = pizzaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> update(@PathVariable Long id, @RequestBody PizzaDto dto) {
        PizzaDto updated = pizzaService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> delete(@PathVariable Long id) {
        PizzaDto deleted = pizzaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
