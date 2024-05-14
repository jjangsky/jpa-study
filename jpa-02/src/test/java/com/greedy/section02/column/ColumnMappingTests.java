package com.greedy.section02.column;

import com.greedy.section01.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColumnMappingTests {
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
    public void columnUsePropertiesTest(){

        // 실행 시점에서 테이블 생성 DDL 구문 진행

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
        member.setMembberRole("user");
        member.setStatus("Y");

        // when
        entityManager.persist(member);

        // then
        Member foundMember = entityManager.find(Member.class, 1);
        foundMember.setNickName("사용자01변경");
        assertEquals(member.getMemberNo(), foundMember);

    }

}
