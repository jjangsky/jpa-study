package com.example.springdatajpa.menu.service;

import com.example.springdatajpa.menu.dto.CategoryDto;
import com.example.springdatajpa.menu.dto.MenuDto;
import com.example.springdatajpa.menu.entity.Category;
import com.example.springdatajpa.menu.entity.Menu;
import com.example.springdatajpa.menu.repository.CategoryRepository;
import com.example.springdatajpa.menu.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Menu> menus = menuRepository.findAll(Sort.by("menuCode")); // 정렬 기준 정할 수 있음
        // Menu Entity를 MenuDto로 변환(ModelMapper 사용)
        return menus.stream().map(menu -> mapper.map(menu, MenuDto.class)).collect(Collectors.toList());
    }

    // findAll 페이징 처리 후
    public Page<MenuDto> findAllMenuList(Pageable pageable) {

        // 넘어온 pageable에 담긴 페이지 번호를 인덱스 개념으로 바꿔서 인지시킴(0부터 시작하는 이유)
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                                    pageable.getPageSize(),
                                    Sort.by("menuCode").descending());

        // Pageable 객체를 인자로 전달(페이지 정보를 구축 후 넘기는 방식)
        Page<Menu> menuList = menuRepository.findAll(pageable);

        return menuList.map(menu -> mapper.map(menu, MenuDto.class));

    }

    // 메뉴 가격으로 조회
    public List<MenuDto> findMenuByPrice(int menuPrice) {

        // 전달 받은 가격을 초과하는 메뉴의 목록을 조회하는 메소드
        List<Menu> menuList = menuRepository.findByMenuPriceGreaterThan(menuPrice);

        return menuList.stream().map(menu -> mapper.map(menu, MenuDto.class)).collect(Collectors.toList());
    }



    // 카테고리 조회(JPQL 사용)
    public List<CategoryDto> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAllCategories();

        return categoryList.stream().map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }



    // insert
    @Transactional
    public void registMenu(MenuDto menuDto) {
        menuRepository.save(mapper.map(menuDto, Menu.class));
    }


    // modify
    @Transactional
    public void modifyMenu(MenuDto menuDto) {

        // modify는 객체를 수정하면 끝
        Menu menu = menuRepository.findById(menuDto.getMenuCode()).orElseThrow(IllegalArgumentException::new);
        menu.setMenuName(menuDto.getMenuName()); // 불러온 객체를 수정하면 DB에 반영됨(반영 시점은  flush 처리할 때)

    }


    // delete
    @Transactional
    public void deleteMenu(int menuCode) {
        menuRepository.deleteById(menuCode);
    }
}
