package com.greedy.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class A_EntityManagerLifeCycleTest
{

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

    @Test
    public void Entity_Manager_Life_Cycle_Test(){
        // 엔티티 매니저 생성
        System.out.println("entityManagerFactory : " + entityManagerFactory.hashCode());
        System.out.println("entityManager : " + entityManager.hashCode());
    }

    @AfterAll
    public static void closeFactory(){
        // 환경 종료
        entityManagerFactory.close();
    }
}
