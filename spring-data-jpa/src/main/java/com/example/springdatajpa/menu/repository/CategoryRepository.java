package com.example.springdatajpa.menu.repository;

import com.example.springdatajpa.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
