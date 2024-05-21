package com.example.springdatajpa.menu.controller;

import com.example.springdatajpa.menu.dto.MenuDto;
import com.example.springdatajpa.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{menuCode}")
    public String findMenuByCode(@PathVariable int menuCode , Model model){
        MenuDto menu = menuService.findMenuByCode(menuCode);
        model.addAttribute("menu", menu);
        return "menu/detail";
    }

    @GetMapping("/list")
    public String findAllMenus(Model model){

        List<MenuDto> menuList = menuService.findAllMenus();
        model.addAttribute("menuList", menuList);
        return "menu/list";
    }

    @GetMapping("/page/list")
    public String findAllMenusPage(@PageableDefault Pageable pageable, Model model){

        Page<MenuDto> menuPage = menuService.findAllMenuList(pageable);

        /**
         * Page 객체 메소드
         * 조회한 내용 목록 : getContent()
         * 총 페이지 수 : getTotalPages()
         * 총 메뉴 수 : getTotalElements()
         * 해당 페이지에 표시 될 요소 수 : getSize()
         * 해당 페이지에 실제 요소 수 : getNumberOfElements()
         * 첫 페이지 여부 : isFirst()
         * 마지막 페이지 여부 : isLast()
         * 정렬 바식 : getSort()
         * 여러 페이지 중 현재 인덱스 : getNumber()
         */


        return "menu/list";
    }

}
