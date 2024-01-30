package com.example.firstproject.repository;

import com.example.firstproject.entity.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
}
