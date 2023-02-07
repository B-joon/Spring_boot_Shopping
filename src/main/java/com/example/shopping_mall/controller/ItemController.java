package com.example.shopping_mall.controller;

import com.example.shopping_mall.vo.ItemFormVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping(value = "/admin/item/new")
    public String itemForm(Model model) {
        model.addAttribute("itemFormVO", new ItemFormVO());
        return "item/itemForm";
    }

}
