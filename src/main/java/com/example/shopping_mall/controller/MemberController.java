package com.example.shopping_mall.controller;

import com.example.shopping_mall.entity.MemberEntity;
import com.example.shopping_mall.service.MemberService;
import com.example.shopping_mall.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormVo", new MemberVO());
        return "member/memberForm";
    }

//    @PostMapping(value = "new")
//    public String memberForm(MemberVO membervo) {
//        MemberEntity member = MemberEntity.createMember(membervo, passwordEncoder);
//        memberService.save(member);
//        return "redirect:/";
//    }

    @PostMapping(value = "/new")
    public String newMember(@Valid MemberVO memberVO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            MemberEntity member = MemberEntity.createMemberAdmin(memberVO, passwordEncoder);
            memberService.save(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessge", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember() {
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberLoginForm";
    }

}
