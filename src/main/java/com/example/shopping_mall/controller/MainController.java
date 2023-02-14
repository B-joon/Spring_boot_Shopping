package com.example.shopping_mall.controller;

import com.example.shopping_mall.service.ItemService;
import com.example.shopping_mall.vo.ItemSearchVO;
import com.example.shopping_mall.vo.MainItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping(value = "/")
    public String main(ItemSearchVO itemSearchVO, Optional<Integer> page, Model model) {

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemVO> items = itemService.getMainItemPage(itemSearchVO, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchVO", itemSearchVO);
        model.addAttribute("maxPage", 5);

        return "main";
    }

}
