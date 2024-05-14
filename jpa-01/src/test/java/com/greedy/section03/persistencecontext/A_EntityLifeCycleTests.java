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

    // 준영속 테스트
    @Test
    public void persistenceContextRemoveTest(){
        Menu menu1 = entityManager.find(Menu.class, 11);
        Menu menu2 = entityManager.find(Menu.class, 12);

        // 준영속 == 비영속도 아닌 영속도 아닌 상태

        /*
        * 영속성 컨텍스트가 관리하던 엔티티 객체를 관리하지 않는 상태가 되게 한 것을 준영속 상태라고 한다.
        * detach 가 준영속 상태를 만들기 위한 메소드.
         */
        entityManager.detach(menu2);

        menu1.setMenuName("아메리카노2");
        menu2.setMenuName("아메리카노3");

        assertEquals("아메리카노2", entityManager.find(Menu.class, 11).getMenuName());
        assertEquals("아메리카노2", entityManager.find(Menu.class, 12).getMenuName());
        // 12번은 영속 상태가 아니므로 다시 DB에서 조회

    }

    // 준영속 Clear
    @Test
    public void persistenceContextClearTest(){
        Menu menu1 = entityManager.find(Menu.class, 11);
        Menu menu2 = entityManager.find(Menu.class, 12);

        /* 영속성 컨텍스트로 관리되던 엔티티 객체들을 모두 비영속 상태로 변경*/
        entityManager.clear();

        menu1.setMenuName("아메리카노2");
        menu2.setMenuName("아메리카노2");

        // DB에서 새로 조회 해온 객체를 영속 상태로 두기 때문에 전혀 다른 결과로 나옴
        assertEquals("아메리카노2", entityManager.find(Menu.class, 11).getMenuName());
        assertEquals("아메리카노2", entityManager.find(Menu.class, 12).getMenuName());
    }
}
