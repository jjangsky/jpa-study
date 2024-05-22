package com.example.springdatajpa.menu.repository;

import com.example.springdatajpa.menu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

//    @Query(value="SELECT m FROM Category m ORDER BY m.categoryCode DESC")

    @Query(value="SELECT CATEGORY_CODE, CATEGORY_NAME, REF_CATEGORY_CODE FROM TBL_CATEGORY" + " ORDER BY CATEGORY_CODE DESC", nativeQuery = true)
    // 네이티브 쿼리 속성값 `TRUE`를 사용하면 JPQL이 아닌 SQL 쿼리를 사용할 수 있음
    List<Category> findAllCategories();
}
