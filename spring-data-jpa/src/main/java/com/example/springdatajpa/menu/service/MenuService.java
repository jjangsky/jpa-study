package com.example.springdatajpa.menu.service;

import com.example.springdatajpa.menu.dto.MenuDto;
import com.example.springdatajpa.menu.repository.CategoryRepository;
import com.example.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *  Service 계층 : 비즈니스 로직, 트랜잭션처리(@Transactional), DTO <-> Entity 변환
 */
@Service
public class MenuService {

    private final ModelMapper mapper;
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MenuService(ModelMapper mapper, MenuRepository menuRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.menuRepository = menuRepository;
        this.categoryRepository = categoryRepository;
    }

    public MenuDto findMenuByCode(int menuCode) {
        return null;
    }
}
