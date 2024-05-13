package com.greedy.section03.persistencecontext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class A_EntityLifeCycleTests {
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
     * 영속성 컨ㅌ텍스트는 엔팉티 매니저가 엔ㅌ티티 객체를 저장하는 공간으로 엔티티 객체를 보관하고 관리한다.
     * 엔티티 매니저가 생성될 때 하나의 영속성 컨텍스트가 만들어진다.
     *
     *
     * 엔티티 생명주기
     * 비영속, 영속, 준영속, 삭제 상태
     */

    @Test
    public void nonPersistedTest() {
        Menu foundmenu = entityManager.find(Menu.class, 1);

        // 영속 상태의 객체에서 값을 추출해 담더라도 새로 생성되어 영속성 컨텍스트와 관련 없는 객체는 비영속 상태이다.
        // 만약 이게 영속 객체였다면 몇번을 받아내도 동일 객체로 판정됨
        Menu newMenu = new Menu();
        newMenu.setMenuCode(foundmenu.getMenuCode());
        newMenu.setMenuName(foundmenu.getMenuName());
        newMenu.setMenuPrice(foundmenu.getMenuPrice());
        newMenu.setCategoryCode(foundmenu.getCategoryCode());
        newMenu.setOrderableStatus(foundmenu.getOrderableStatus());

        boolean isTrue = (foundmenu == newMenu);
        assertFalse(isTrue);
    }

    @Test
    public void continuePersistenceSelectTest(){
        Menu foundMenu = entityManager.find(Menu.class, 1);
        Menu foundMenu2 = entityManager.find(Menu.class, 1);

        // 조회한 엔티티 객체는 영속성 컨텍스트에 의해 관리되기 때문에 조회한 엔티티 객체는 동일한 객체로 판정된다.
        boolean isTrue = (foundMenu == foundMenu2);
        assertFalse(isTrue);

    }

    @Test
    public void persistenceContextAddTest() {
        Menu menu = new Menu();
        menu.setMenuName("아메리카노");
        menu.setMenuPrice(4000);
        menu.setCategoryCode(1);
        menu.setOrderableStatus("Y");

        // 엔티티 매니저를 통해 엔티티를 저장하면 영속성 컨텍스트에 저장된다.
        entityManager.persist(menu);

        // 영속성 컨텍스트에 저장된 엔티티는 영속 상태이다.
        boolean isTrue = entityManager.contains(menu);
        assertFalse(isTrue);
    }

    @Test
    public void persistenceChangeValue(){
        Menu menu = new Menu();
        menu.setMenuName("아메리카노");
        menu.setMenuPrice(4000);
        menu.setCategoryCode(1);
        menu.setOrderableStatus("Y");

        entityManager.persist(menu);
        menu.setMenuName("아메리카노2");

        Menu foundMenu = entityManager.find(Menu.class, 500);

        assertEquals("아메리카노2", foundMenu.getMenuName());
    }
}
