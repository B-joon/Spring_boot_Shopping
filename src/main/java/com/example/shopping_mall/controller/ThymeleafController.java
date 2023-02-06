package com.example.shopping_mall.controller;

import com.example.shopping_mall.vo.ItemVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafController {

    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model) {
        model.addAttribute("data", "타임리프 예제 입니다.");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value = "/ex02")
    public String thymeleafExample02 (Model model) {
        ItemVO item = new ItemVO();
        item.setItemDetail("상품 상세 설명");
        item.setItemNm("테스트 상품1");
        item.setPrice(10000);
        item.setRegTime(LocalDateTime.now());

        model.addAttribute("itemvo",item);
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model model) {

        List<ItemVO> itemList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ItemVO item = new ItemVO();
            item.setItemDetail("상품 상세 설명" + i);
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setRegTime(LocalDateTime.now());
            itemList.add(item);
        }

        model.addAttribute("itemList", itemList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value = "/ex04")
    public String thymeleafExample04 (Model model) {

        List<ItemVO> itemList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ItemVO item = new ItemVO();
            item.setItemDetail("상품 상세 설명" + i);
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setRegTime(LocalDateTime.now());
            itemList.add(item);
        }

        model.addAttribute("itemList", itemList);

        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping(value = "/ex05")
    public String thymeleafExample05 (Model model) {
        return "thymeleafEx/thymeleafEx05";
    }

    @GetMapping(value = "/ex06")
    public String thymeleafExample06(String param1, String param2, Model model) {
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "thymeleafEx/thymeleafEx06";
    }

    @GetMapping(value = "/ex07")
    public String thymeleafExample07() {
        return "thymeleafEx/thymeleafEx07";
    }

}
