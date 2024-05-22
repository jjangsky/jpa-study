package com.example.springdatajpa.menu.repository;

import com.example.springdatajpa.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    // Entity 클래스와 함께 사용하는 Repository 인터페이스
    // JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성

    List<Menu> findByMenuPriceGreaterThan(int menuPrice);
}
