package com.greedy.section02.onetomany;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="category_and_menu")
@Table(name="tbl_category")
public class CategoryAndMenu {

    @Id
    @Column(name="CATEGORY_CODE")
    private int categoryCode;

    @Column(name="CATEGORY_NAME")
    private String categoryName;

    @Column(name="REF_CATEGORY_CODE")
    private Integer refCategoryCode;

    @JoinColumn(name="CATEGORY_CODE")
    @OneToMany
    private List<Menu> menuList;


}
