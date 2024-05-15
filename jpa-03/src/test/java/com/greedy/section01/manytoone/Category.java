package com.greedy.section01.manytoone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Category_section01")
@Table(name = "tbl_category")
public class Category {
    /*
     * 메뉴와 카테고리 입장에서 메뉴가 카테고리를 주시할 때 둘의 관계는 N:1이다.
     */

    @Id
    @Column(name = "CATEGORY_CODE")
    private int categoryCode;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;
    @Column(name = "REF_CATEGORY_CODE")
    private Integer refCategoryCode;

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }

    public Category() {

    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }
}
