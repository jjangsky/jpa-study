package com.greedy.section03.persistencecontext;

import jakarta.persistence.*;

@Entity(name = "section03_MENU")  // 엔티티 객체로 만들기 위한 어노테이션, 다른 패키지에 동일한 이름의 클래스가 존재하면 안됨
@Table(name = "TBL_MENU")         // 데이터베이스에 매핑 될 테이블 이름 설정
public class Menu {

    @Id                             // PK에 해당하는 속성을 지정
    @Column(name = "MENU_CODE")     // 데이터베이스에 대응되는 컬럼명 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    // 기본키값을 데이터베이스에 생성하도록 지정 - PK 제약조건
    // 데이터베이스는 테이블에 새로운 행이 추가될 때마다 자동으로 값을 증가시키는 기능을 제공
    private int menuCode;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "MENU_PRICE")
    private int menuPrice;

    @Column(name = "CATEGORY_CODE")
    private int categoryCode;

    @Column(name = "ORDERALBE_STATUS")
    private String orderableStatus;

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }
}
