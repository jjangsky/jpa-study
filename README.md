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

### **엔티티 생명주기**

**영속성 컨텍스트는 엔티티 매니저가 엔티티 객체를 저장하는 공간으로 엔티티 객체를 보관하고 관리한다.**

> 📌 **엔티티 생명주기**
>
> -   `비영속`: 영속성 컨텍스트와 전혀 관계가 없는 상태
> -   `영속`: 영속성 컨텍스트에 저장된 상태
> -   `준영속`: 영속성 컨텍스트에 저장되었다가 분리된 상태
> -   `삭제`: 삭제된 상태(영속성 컨텍스트 삭제)
> -   `병합`: 준영속 상태인 엔티티가 다시 영속상태로 변경된 상태

## JPA-02[Entity-Mapping]

### **Entity Annotation**

**엔티티에 매핑 작업을 하기 위한 어노테이션**

`@Entity` **테이블과 매핑할 엔티티 클래스**

`@Table` **엔티티 클래스에 매핑할 테이블 정보를 name 속성을 사용하여 매핑**

-   생략하면 클래스 이름으로 테이블명과 매핑함

`@Id` **엔팉티 클래스의 해당 필드와 테이블의 기본키(Primary Key)와 매핑한다.**

-   해당 필드는 식별자 필드라 하며 엔티티 클래스의 필수값이다

`Column` **엔티티 클래스의 해당 필드와 테이블의 컬럼을 name 속성을 사용하여 매핑**

-   매핑 어노테이션을 생략하면 필드명을 사용해서 해당 컬럼명과 매핑한다.
-   대소문자를 구분하는 데이터베이스를 사용하면 명시적으로 대문자 컬럼명과 매핑해야함

### **PrimaryKey 설정**

데이터베이스에서 테이블은 데이터를 식별하기 위한 `PK컬럼`이 필요함

**JPA에서는 `strategy` 전략과 `SeqTable` 두 가지로 기본키를 생성할 수 있다.**

🔑 **Strategy 자동 생성 전략**

-   **엔티티 클래스에서 `@GeneratedValue`어노테이션으로 식별키를 자동으로 생성할 수 있다.**
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/42f4532d-9415-4afa-a63b-b71ca65831cb/6905179f-de6d-463f-a8fb-296c5414ce3b/Untitled.png)

> 📌 **stratege 종류**
>
> -   `GenerationType.IDENTITY` : 기본 키 생성을 DB에 위임(Mysql 또는 MariaDB auto_increment)
> -   `GenerationType.SEQUENCE`: DB에 시퀀스를 사용하여 기본 키를 생성
> -   `GenerationType.TABLE` : 키 생성 테이블을 사용하여 기본 키를 생성
> -   `GenerationType.AUTO` : DB 벤더에 따라 `IDENTITY`, `SEQUENCE`, `TABLE` 중 하나를 자동으로 선택

### **복합키(composite-Key) 설정**

복합키를 따로 저장하기 위한 PK 저장용 객체가 있어야 함.
`@Embeddable`과 `@IdClass` 두가지 방식이 존재함

|                              | `@Embeddable`                   | `@IdClass`                          |
| ---------------------------- | ------------------------------- | ----------------------------------- |
| 주요 어노테이션              | `@Embeddable`, `@EmbeddedId`    | `@IdClass`, `@Id` (엔티티 클래스에) |
| 복합키 클래스 정의           | 엔티티 클래스 내부에 정의       | 별도의 클래스로 정의                |
| 엔티티 클래스에서            | `@EmbeddedId`로 복합키 지정     | `@Id`로 각 PK 필드를 직접 지정      |
| 복합키 객체 타입             | 엔티티 클래스의 필드로 사용     | 엔티티 클래스와 별도로 사용         |
| `equals`와 `hashCode` 메소드 | 반드시 오버라이딩 필요          | 반드시 오버라이딩 필요              |
| 장점 및 용도                 | 객체지향적인 설계 가능, 더 유연 | 직관적인 설정 가능                  |

## JPA-03[Association]

**Association Mapping은 Entity 클래스 간의 관계를 매핑하는 것을 의미함**

-   JDBC, Mybatis에서 쿼리문 보낼 때 Join관계를 맺기 위한 목적임

이를 통해 객체를 이용해 데이터베이스의 **테이블 간의 관계를 활용해 한번에 객체를 조회**할 수 있다.

### **다중성에 의한 분류**

**연관 관계가 있는 객체 관계에서 실제로 연관을 가지는 객체의 수에 따라 분류한다.**

-   `N:1 (Many To One)` 다대일 관계
-   `1:N(One To Many)` 일대다 관계
-   `1:1(One To One)`일대일 관계
-   `N:M(Many To Many)` 다대다 관계

가장 대표적으로 많이 쓰이는 관계가 `N:1` 관계이며 사실 그 외에는 잘 쓰이지도 않고 추천도 하지 않는다. → 심지어 `N:M` 연관 관계는 잘못 사용하면 순환참조 유발

### **방향에 따른 분류**

**테이블의 연관 관계는 외래 키를 이용하여 양방향 연관 관계의 특징을 가진다**.

**반면, 객체는 참조에 의한 연관관계로 단방향이다.**

객체 간의 연관 관계를 양방향으로 만들고 싶을 경우 **반대 쪽에서도 필드를 추가해서 참조를 보관**하면 된다.

-   엄밀하게 따지면 양방향 관계가 아닌 단방향 관계를 2개로 맺은 것으로 볼 수 있음

`단방향(Unidirectional)` → 한 쪽에서만 참조를 보관

`양방향(Bidirectional)` → 양쪽에서 서로 참조를 보관

## JPA-04[JPQL]

**`JPQL`은 Java Persistence Query Language의 약자로, Java에서 제공하는 영속성 API인 JPA에서 사용하는 쿼리 언어입니다.**

SQL과 비슷한 문법을 가지고 있지만, 객체 지향적인 쿼리를 사용할 수 있는 것이 특징입니다.

이를 통해 개발자는 데이터베이스 테이블이 아닌 자바 객체에 집중할 수 있게 됩니다.

> 📌 JPQL 특징
>
> -   SELECT, UPDATE,DELETE 등의 키워드 사용은 SQL과 동일
> -   INSERT는 persist() 메소드를 사용
> -   키워드는 대소문자를 구분하지 않지만, 속성은 대소문자를 구분하므로 유의
> -   엔티티 별칭은 필수는 아니지만 사용하는 것을 권고함\*\*

---

### **JPQL 사용 방법**

1. **작성한 jpql(문자열 쿼리)을 `entityManager.createQuery()` 메소드를 이용해 Query 객체로 생성**
2. **쿼리 객체는 `TypedQuery` 와 `Query` 두 가지가 있음**
    - `TypedQuery` : 반환할 타입을 명확하게 지칭하는 방식일 때 사용하며 쿼리 객체의 메소드 실행결과로 지정한 타입이 반환된다.
    - `Query` : 반환할 타입을 명확하게 지정할 수 없을 때 사용하며 쿼리 객체 메소드의 실행 결과로 Object 또는 Objectp[ ]이 반환된다.
3. **쿼리 객체에서 제공하는 메소드를 호출해서 쿼리를 실행하고 데이터베이스를 조회한다.**

    3-1. `getSingleResult()` : 결과가 정확히 한 행일 경우 사용하며 없거나 많으면 예외가 발생한다.

    3-2. `getResultList( )` : 결과가 2행 이상일 경우 사용하며 컬렉션을 반환한다. (없으면 빈 컬렉션 반환)
