package com.example.springdatajpa.menu.service;

import com.example.springdatajpa.menu.dto.MenuDto;
import com.example.springdatajpa.menu.entity.Menu;
import com.example.springdatajpa.menu.repository.CategoryRepository;
import com.example.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // findById 예제
    public MenuDto findMenuByCode(int menuCode) {

        // 메소드의 반환 객체는 옵셔널 타입
        Menu menu = menuRepository.findById(menuCode).orElseThrow(IllegalArgumentException::new);
        // Menu Entity를 MenuDto로 변환(ModelMapper 사용)
        return mapper.map(menu, MenuDto.class);
    }

    // findAll 예제
    public  List<MenuDto> findAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        // Menu Entity를 MenuDto로 변환(ModelMapper 사용)
        return mapper.map(menus, List.class);
    }
}
