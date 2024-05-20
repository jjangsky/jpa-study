package com.greedy.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleJPQLTests {

    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactiory(){
        // 환경 생성
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");

    }

    @BeforeEach
    public void initManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }



    @AfterAll
    public static void closeFactory(){
        // 환경 종료
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager(){
        // 엔티티 매니저 종료
        entityManager.close();
    }


    @Test // 단일행 단일열 조회 테스트 - TypedQuery
    public void TypeQueryTest() {
        String jpql = "SELECT m FROM menu m where m.menuCode = 7";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);

        String resultMenuName = query.getSingleResult();
        System.out.println("resultMenuName : " + resultMenuName);

        assertEquals("치즈볼", resultMenuName);
    }

    @Test // 단일행 단일열 조회 테스트 - Query
    public void QueryTest() {
        String jpql = "SELECT m.menuName FROM menu m where m.menuCode = 7";
        Query query = entityManager.createQuery(jpql);

        String resultMenuName = (String) query.getSingleResult();
        System.out.println("resultMenuName : " + resultMenuName);

        assertEquals("치즈볼", resultMenuName);
    }

    @Test // 다중행 다중열 조회 테스트 - TypedQuery
    public void TypeQueryMultiResultTest() {
        String jpql = "SELECT m.menuCode, m.menuName FROM menu m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);

       List<Menu> foundMenuList = query.getResultList();
        System.out.println("foundMenuList : " + foundMenuList);

        assertEquals(5, foundMenuList.size());
    }
}
