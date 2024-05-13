# jpa-study

JPA 기초 개념 정리
노션 : https://jjangsky.notion.site/JPA-6c7ebcc650b546aeb3a8adb396274594

## JPA-01[Persistence-context]

### 영속성 컨텍스트(PersistenceContext)란?

**엔티티를 영구 저장하는 환경이라는 뜻이며 App과 DB 사이에 객체를 보관하는 가상의 데이터베이스 같은 역할을 한다.**

엔티티 매니저를 통해 엔티티를 저장하거나 조회하면 **엔티티 매니저는 영속성 컨텍스트에 엔티티를 보관하고 관리**한다.

-   초기 영속성 컨텍스트는 빈 공간

영속성 컨텍스트는 엔티티를 **Key-Value 형태로 저장**하는 저장소이며 **엔티티 매니저를 생성할 때 같이 하나 생성**된다.

**엔티티 매니저를 통해서 영속성 컨텍스트에 접근**할 수 있고 관리할 수 있다.

### 엔티티 매니저 팩토리(EntityManagerFactory)

**엔티티 매니저를 생성할 수 있는 기능을 제공하는 팩토리 Class**

`Thread-safe` 하기 때문에 여러 스레드가 동시에 접근해도 안전하고 서로 다른 스레드 간 공유해서 재사용한다.\*\*

-   `Thread-safe` 는 말 그대로 여러 **스레드가 한번에 접근하는 환경에서 안전**하다는 뜻
    -   톰캣같은 경우 하나의 요청마다 하나의 스레드를 생성하고 여러 사용자가 동시에 요청하면 여러 개의 스레드가 생기는 멀티 스레드 환경이다.
    -   이런 멀티 스레드 요청이 들어와도 매니저 팩토리 영역에서는 순차적으로 하나씩 처리

`Thread-safe` 한 기능을 요청 스코프마다 생성하기에는 비용(시간, 메모리) 부담이 커서 App 스코프와 동일하게 **싱글톤으로 생성해서 관리**

-   따라서 DB를 사용하는 애플리케이션 당 한 개의 EntityManagerFactory를 생성

### 엔티티 매니저(EntityManager)

**엔티티 매니저는 엔티티를 저장하는 메모리 상의 데이터베이스를 관리하는 인스턴스**

**엔티티를 저장하고, 수정, 삭제 ,조회하는 등의 엔티티와 관련된 모든 일을 한다.**

엔티티 매니저는 **Thread-safe 하지 않아 동시성 문제**가 발생할 수 있음

-   따라서 스레드 간 공유하지 않고 Web의 경우 일반적으로 request scope와 일치시킴

```java
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
```

-   엔티티 팩토리 생성하고 엔티티 매니저 객체를 생성
-   사용이 끝난 경우 매니저를 종료하고 환경을 종료 필수 → 메모리 누수

## JPA-02

Mapping
