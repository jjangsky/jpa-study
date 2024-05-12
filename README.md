# jpa-study

JPA 기초 개념 정리
노션 : https://www.notion.so/jjangsky/JPA-6c7ebcc650b546aeb3a8adb396274594

## JPA-01

Persistence-context

EntityManagerFactory는 데이터베이스와의 연결을 설정하고, EntityManager를 생성하는 역할
EntityManagerFactory를 생성하고 EntityManager를 통해 데이터베이스와 상호작용할 때,
각 EntityManager는 특정한 작업을 수행하는 독립적인 인스턴스이다.

따라서 EntityManagerFactory를 통해 생성된 각 EntityManager 인스턴스는 서로 다른 객체로 간주됩니다.
(갖고 있는 hashCode 값들이 다름)

## JPA-02

Mapping
