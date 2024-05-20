package com.example.springdatajpa.menu.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDto {
    private int categoryCode;
    private String categoryName;
    private Integer refCategoryCode;

}
