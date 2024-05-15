package com.greedy.section01.manytoone;

import jakarta.persistence.*;

@Entity(name = "menu_and_category")
@Table(name = "tbl_menu")
public class MenuAndCategory {

    @Id
    @Column(name = "MENU_CODE")
    private int menuCode;
    @Column(name = "MENU_NAME")
    private String menuName;
    @Column(name = "MENU_PRICE")
    private int menuPrice;


    // 메뉴 여러개가 하나의 카테고리에 묶이기 때문에 ManytoOne 관계
    @JoinColumn(name = "CATEGORY_CODE") // FK 컬럼의 이름 명시
    @ManyToOne
    private Category category;
    @Column(name = "ORDERABLE_STATUS")
    private String orderabbleStatus;

    public MenuAndCategory(int menuCode, String menuName, int menuPrice, Category category, String orderabbleStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderabbleStatus = orderabbleStatus;
    }

    public MenuAndCategory() {

    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrderabbleStatus() {
        return orderabbleStatus;
    }

    public void setOrderabbleStatus(String orderabbleStatus) {
        this.orderabbleStatus = orderabbleStatus;
    }
}
