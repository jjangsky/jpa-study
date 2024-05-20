package com.example.springdatajpa.menu.controller;

import com.example.springdatajpa.menu.dto.MenuDto;
import com.example.springdatajpa.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        return "menu/list";
    }

}
