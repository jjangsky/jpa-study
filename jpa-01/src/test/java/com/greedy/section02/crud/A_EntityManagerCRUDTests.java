package com.greedy.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerCRUDTests {
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
    public void meun_select_test(){
        //given
        int menuCode = 2;
        //when
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        Menu foundMenu2 = entityManager.find(Menu.class, menuCode); // 조회 안나라감

        //then
        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
    }

    @Test
    public void menu_insert_test(){
        //given
        Menu menu = new Menu();
        menu.setMenuName("아메리카노");
        menu.setMenuPrice(4000);
        menu.setCategoryCode(1);
        menu.setOrderableStatus("Y");

        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.persist(menu);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }

        //then
        Assertions.assertTrue(entityManager.contains(menu));  // 현재 메뉴 객체가 영속 상태로 관리되고 있는지 확인
    }

    @Test
    public void menu_update_test(){
        //given
        Menu menu = entityManager.find(Menu.class, 2);
        // 여기서 menu는 영속상태이다.
        // 즉, 무조건 찾아와라 라는 뜻이 아닌 기존에 캐시 영역에 있으면 가져오고
        // 없으면 조회해서 가져와라, 관리를 하라는 뜻
        System.out.println("menu = " + menu);

        String updateMenuName = "아메리카노";

        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            menu.setMenuName(updateMenuName);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }

        //then
        Assertions.assertEquals(updateMenuName, entityManager.find(Menu.class, 2).getMenuName());
    }

    @Test
    public void menu_delete_test(){
        //given
        Menu menu = entityManager.find(Menu.class, 2);

        //when
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try{
            entityManager.remove(menu);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }

        //then
        Assertions.assertNull(entityManager.find(Menu.class, 2));
    }

}
