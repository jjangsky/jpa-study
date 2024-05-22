package com.example.springdatajpa.menu.controller;

import com.example.springdatajpa.common.Pagination;
import com.example.springdatajpa.common.PagingButtonInfo;
import com.example.springdatajpa.menu.dto.CategoryDto;
import com.example.springdatajpa.menu.dto.MenuDto;
import com.example.springdatajpa.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        PagingButtonInfo paging = Pagination.getPagingButtonInfo(menuPage);
        model.addAttribute("paging", paging); // 페이징 관련 정보
        model.addAttribute("menuPage", menuPage);


        return "menu/list";
    }

    // 요청 메소드를 `void` 형으로 정의하면 요청 이름 자체가 반환 주소가 됨
    // /menu/querymethod 로 페이지 이동 처리 됨
    @GetMapping("/querymethod")
    public void queryMethodPage(){}


    // 조건 검색
    @GetMapping("/search")
    public String findMenuPrice(@RequestParam int menuPrice, Model model){

        List<MenuDto> menuList = menuService.findMenuByPrice(menuPrice);
        model.addAttribute("menuList", menuList);
        model.addAttribute("menuPrice", menuPrice);

        return "menu/searchResult";
    }


    // 이동 페이지
    @GetMapping("/regist")
    public void registMenuPage(){}



    //  화면 최초 렌더링 시, 메뉴 카테고리 목록을 조회
    @GetMapping(value = "/regist",  produces = "application/json; charset=utf8")
    @ResponseBody
    /*
      메소드에 @ResponseBody 어노테이션을 붙이면, 메소드의 반환 값이 ViewResolver를 거치지 않고 HTTP Response Body에 직접 쓰여짐
     */
    public List<CategoryDto> registMenuPageJson(){
        return menuService.findAllCategories();
    }


    // DML 작업
    @PostMapping("/regist")
    public String registMenu(MenuDto menuDto){
        menuService.registMenu(menuDto);
        return "redirect:/menu/list";
    }


}
