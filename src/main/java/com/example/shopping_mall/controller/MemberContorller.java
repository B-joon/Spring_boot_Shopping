package com.example.shopping_mall.controller;

import com.example.shopping_mall.entity.MemberEntity;
import com.example.shopping_mall.service.MemberService;
import com.example.shopping_mall.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberContorller {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormVo", new MemberVO());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memeberForm(MemberVO membervo) {
        MemberEntity member = MemberEntity.createMember(membervo, passwordEncoder);
        memberService.save(member);
        return "redirect:/";
    }

}
