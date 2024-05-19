package com.greedy.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManyToOneAssociationTests {

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

    @Test
    public void manyToOneObjectMappingTest() {
        // 객체 매핑 테스트

        int menuCode = 15;
        MenuAndCategory foundMenu = entityManager.find(MenuAndCategory.class, menuCode);
        Category menuCategory = foundMenu.getCategory();

        assertNotNull(menuCategory);
        System.out.println("menuCategory = " + menuCategory);
        System.out.println("foundMenu = " + foundMenu);
    }
}
