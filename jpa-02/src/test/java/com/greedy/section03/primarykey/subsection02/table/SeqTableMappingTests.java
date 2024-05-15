package com.greedy.section03.primarykey.subsection02.table;

import com.greedy.section03.primarykey.subsection01.identity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

public class SeqTableMappingTests {

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
    public void primaryMappingTest(){

        // given
        Member member = new Member();
//        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickName("사용자01");
        member.setPhone("010-1234-5678");
        member.setEmail("test@naver,com");
        member.setAddress("서울시 강남구 역삼동");
        member.setEnrollDate(new Date(System.currentTimeMillis()));
        member.setMembberRole("user");
        member.setStatus("Y");

        Member member2 = new Member();
//        member2.setMemberNo(1);
        member2.setMemberId("user02");
        member2.setMemberPwd("pass02");
        member2.setNickName("사용자02");
        member2.setPhone("010-1234-4678");
        member2.setEmail("test2@naver,com");
        member.setAddress("서울시 강남구 역삼동1");
        member2.setEnrollDate(new Date(System.currentTimeMillis()));
        member2.setMembberRole("user");
        member2.setStatus("Y");

        // when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityManager.persist(member2);
        entityTransaction.commit();

        // then
//        Member selectedMember = entityManager.find(Member.class, 1);
//        System.out.println("selectedMember : " + selectedMember);
        // 정상적으로 pk 값이 들어갔으면 출력 완료


        // 다중행 조회를 위한 JPQL
        String jpql = "SELECT A.memberNo FROM MEMBER_SUB02 A";
        List<Integer> memberNoList = entityManager.createQuery(jpql, Integer.class).getResultList();
        System.out.println("memberNoList : " + memberNoList);
    }
}
