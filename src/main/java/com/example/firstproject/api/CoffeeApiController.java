package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoffeeApiController {
    @Autowired private CoffeeRepository coffeeRepository;

    // GET
    @GetMapping("/api/coffees")
    public List<Coffee> index() {
        return (List<Coffee>) coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        return (coffee.getId() != null) ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(coffee) : ResponseEntity.status(HttpStatus.OK).body(null);
    }

    // POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        if(coffee.getId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        Coffee created = coffeeRepository.save(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        // 1. entity 갖고오기
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 2. 비정상 처리
        if(target == null || id != coffee.getId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        // 3. 부분 수정
        target.patch(coffee);
        // 4. 정상 처리
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        // 1. entity 갖고오기
        Coffee target = coffeeRepository.findById(id).orElse(null);
        // 2. 비정상 요청 처리
        if(target == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        // 3. 정상 처리
        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
