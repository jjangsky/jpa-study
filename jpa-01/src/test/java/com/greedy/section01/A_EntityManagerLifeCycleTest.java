package com.greedy.section01;

import com.greedy.section02.crud.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class A_EntityManagerLifeCycleTest
{
    /*
     * 주석
     * 엔티티 매니저 팩토리(EnitiyManangerFactory)란?
     * 엔ㅌ티티 매니저를 생성할 수 있는 기능을 제공하는 팩토리 클래스이다.
     * Thread-safe 하기 때문에 여러 스레드가 동시에 접근해도 안전하므로 서로 다른 스레드 간 공유해서 재사용한다.
     * thread-safe한 기능을 요청 스코프마다 생성하기에는 비용(시간과 메모리) 부담이 크므로
     * application 스코프와 동일하게 싱글톤으로 생성해서 관리하는 것이 효율적이다.
     * 따라서 데이터베이스를 사용하는 애플리케이션 당 한 개의 EntitiyManagerFactory를 생성한다.
     *
     * 여기서 Thread-safe 는 톰캣 같은 경우는 하나의 요청마다 하나의 쓰레드를 생성하고
     * 여러 사용자가 동시에 사용하면 여러개의 쓰레드를 동시에 생성하고 사용하는 멀티 스레드 환경이다.
     * 이때 Thread-safe 하다는 것은 여러 쓰레드가 동시에 접근해도 안전하다는 것을 의미한다.
     * 매니저 팩토리에서 접근하는 쓰레드를 동시에 처리하는 것이 아닌 하나씩 처리함
     *
     *
     * 엔티티 매니저(EntityManager)란?
     * 엔티티 매니저는 엔티티를 저장하는 메모리 상의 데이터베이스를 관리하는 인스턴스이다.
     * 엔티티를 저장하고, 수정, 삭제, 조회하는 등의 엔티티와 관련된 모든 일을 한다.
     * 엔티티 매니저는 thread-safe하지 않기 때문에 동시성 문제가 발생할 수 있다.
     * 따라서 스레드 간 공유를 하지 않고, web의 경우 일반적으로 request scope와 일치시킨다.
     *
     * 영속성 컨텍스트(PersistenceContext)란?
     * 엔티티 매니저를 통해 엔티티를 저장하거나 조회하면 엔티티 매니저는 영속성 컨텍스트에 엔티티를 보관하고 관리하단.
     * 영속성 컨텍스트는 엔티티를 key-value 형태로 저장하는 저장소이다.
     * 영속성 컨텍스트는 엔티티 매니저를 생성할 때 같이 하나 만들어진다.
     * 그리고 엔티티 매니저를 통해서 영속성 컨텍스트에 접근할 수 있고 관리할 수 있다.
     */
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
}
