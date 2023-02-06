package com.example.shopping_mall.controller;

import com.example.shopping_mall.service.MemberService;
import com.example.shopping_mall.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberContorller {

    private final MemberService memberService;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormVo", new MemberVO());
        return "member/memberForm";
    }

}
