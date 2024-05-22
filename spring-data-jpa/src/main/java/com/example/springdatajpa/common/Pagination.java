package com.example.springdatajpa.common;

import org.springframework.data.domain.Page;

public class Pagination {

    // PagingButtonInfo를 생성해서 (버튼 생성에 필요한 정보들 생성) 반환하는 static 메소드
    public static PagingButtonInfo getPagingButtonInfo(Page page){
        // 이 때 매개변수로 넘어오는 Page 객체는 인덱스 개념을 갖고 있음
        int currentPage = page.getNumber() + 1;     // 인덱스 개념 -> 실제 페이지 번호의 개념으로 다시 변경
        int defaultButtonCount = 5;                 // 한 페이지에 보일 버튼 최대 갯수
        int startPage;                              // 한 페이지에 보여질 첫 버튼
        int endPage;                                // 한 페이지에 보여질 마지막 버튼

        startPage = (int)(Math.ceil((double)currentPage / defaultButtonCount) - 1) * defaultButtonCount + 1;
        endPage = startPage + defaultButtonCount - 1;
        // 마지막 페이지가 총 페이지 수보다 크면 총 페이지 수로 설정
        if(page.getTotalPages() < endPage){
            endPage = page.getTotalPages();
        }

        if (page.getTotalPages() == 0){
            endPage = startPage;
        }

        return new PagingButtonInfo(currentPage, startPage, endPage);

    }
}
