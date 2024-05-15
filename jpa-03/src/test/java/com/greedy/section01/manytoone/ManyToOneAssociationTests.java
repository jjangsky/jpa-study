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
    /*
     * Association Mapping은 Entity 클래스 간의 관계를 매핑하는 것을 의미함(Join을 위함)
     * 이를 통해 겍체를 이용해 데잍터베이스의 테이블 간의 관계를 활용해 한번에 객체로 조회할 수 있다.
     *
     * 1. 다중성에 의한 분류
     * : 연관 관계가 있는 객체 관계에서 실제로 연관을 가지는 객체의 수에 따라 분류한다.
     *  - N:1(Many To One) : 다대일 관계
     * - 1:N(One To Many) : 일대다 관계
     * - 1:1(One To One) : 일대일 관계
     * - N:M(Many To Many) : 다대다 관계
     *
     * 2. 방향에 따른 분류
     * : 테이블의 연관 관계는 외래 키를 이용하여 양방향 연관 관계의 특징을 가진다.
     * 반면, 객체는 참조에 의한 연관관계로 단방향이다.
     * 객체 간의 연관 관계를 양방향으로 만들고 싶을 경우 반대 쪽에서도 필드를 추가해서 참조를
     * 보관하면 된다. 하지만 엄밀하게 양방향 관계가 아니라 단방향 관계를 2개로 볼 수 있다.
     * - 단방향(Unidirectional) : 한 쪽에서만 참조를 보관
     * - 양방향(Bidirectional) : 양쪽에서 서로 참조를 보관
     */
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
