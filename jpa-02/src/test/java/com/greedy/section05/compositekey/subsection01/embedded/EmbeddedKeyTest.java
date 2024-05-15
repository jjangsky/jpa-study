package com.greedy.section05.compositekey.subsection01.embedded;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmbeddedKeyTest {

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
    public void embeddedKeyTest(){

        Member member = new Member();
        member.setMemberPK(new MemberPk(1, "user01"));
        member.setPhone("010-1234-5678");
        member.setAddress("서울시 강남구 역삼동");

        entityManager.getTransaction().begin();
        entityManager.persist(member);
        entityManager.getTransaction().commit();

        Member foundMember = entityManager.find(Member.class, member.getMemberPK());
        assertEquals(member.getMemberPK(), foundMember.getMemberPK());
    }
}
