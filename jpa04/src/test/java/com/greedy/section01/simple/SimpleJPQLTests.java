package com.greedy.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleJPQLTests {

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

    /**
     *  [JPQL]
     *  - SELECT, UPDATE, DELETE 등의 키워드 사용은 SQL과 동일하다
     *  - INSERT는 persist() 메소드를 사용한다.
     *  - 키워드는 대소문자를 구분하지 않지만, 엔티티와 속성은 대소문자를 구분하므로 유의한다.
     *  - 엔티티 별칭을 필수로 사용해야 하며 별칭 없이 작성하면 에러가 발생한다.
     */

    /**
     * jpql 사용 방법
     * 1. 작성한 jpql(문자열)을 `entityManager.createQuery()` 메소드를 이용해 Query 객체로 생성한다.
     * 2. 쿼리 객체는 `TypedQuery`와 `Query` 두 가지가 있다.
     *      2-1. TypedQuery : 반환할 타입을명확하게 지칭하는 방식일 때 사용하며 쿼리 객체의 메소드 실행결과로 지정한 타입이 반환된다.
     *      2-2. Query : 반환할 타입을 명확하게 지정할 수 없을 때 사용하며 쿼리 객체 메소드의 실행 결과로 Object 또는 Objectp[]이 반환된다.
*    * 3. 쿼리 객체에서 제공하는 메소드를 호출해서 쿼리를 실행하고 데이터베이스를 조회한다.
     *      3-1. getSingleResult(): 결과가 정확히 한 행일 경우 사용하며 없거나 많으면 예외가 발생한다.
     *      3-2. getResultList(): 결과가 2행 이상일 경우 사용하며 켈력션을 반환한다. 없으면 빈 컬렉션 반환
     */
    @Test // 단일행 단일열 조회 테스트 - TypedQuery
    public void TypeQueryTest() {
        String jpql = "SELECT m FROM menu m where m.menuCode = 7";
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);

        String resultMenuName = query.getSingleResult();
        System.out.println("resultMenuName : " + resultMenuName);

        assertEquals("치즈볼", resultMenuName);
    }

    @Test // 단일행 단일열 조회 테스트 - Query
    public void QueryTest() {
        String jpql = "SELECT m.menuName FROM menu m where m.menuCode = 7";
        Query query = entityManager.createQuery(jpql);

        String resultMenuName = (String) query.getSingleResult();
        System.out.println("resultMenuName : " + resultMenuName);

        assertEquals("치즈볼", resultMenuName);
    }
}
