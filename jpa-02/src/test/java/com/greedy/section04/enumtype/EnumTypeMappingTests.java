package com.greedy.section04.enumtype;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumTypeMappingTests {

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
    public void enumTypeMappingTest(){

        // given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickName("사용자01");
        member.setPhone("010-1234-5678");
        member.setEmail("test@naver,com");
        member.setAddress("서울시 강남구 역삼동");
        member.setEnrollDate(new Date(System.currentTimeMillis()));
//        member.setMembberRole("user");

        /*
            테이블에 insert 할 때,
            1. @Enumerated(EnumType.STRING)을 사용하면 문자열로 값이 들어감(ex: ADMIN, MANAGER)
            2. @Enumerated(EnumType.ORDINAL)을 사용하면 숫자로 값이 들어감(ex: 0, 1)
         */

        member.setMembberRole(RoleType.MANAGER); // 일종의 제약조건으로 관리 가능함
        member.setStatus("Y");

        entityManager.persist(member);
        Member foundMember = entityManager.find(Member.class, member.getMemberNo());
        assertEquals(member.getMemberNo(), foundMember.getMemberNo());
        System.out.println("foundMember.getMembberRole() = " + foundMember.getMembberRole());

    }
}
