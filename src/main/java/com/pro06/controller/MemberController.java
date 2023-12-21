package com.pro06.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

//    private MemberService memberService;

    // 로그인
    @GetMapping("/login")
    public String loginForm(){
        return "member/login";
    }

    // 회원약관
    @GetMapping("/term")
    public String Term(){
        return "member/term";
    }

    // 회원가입
    @GetMapping("/join")
    public String joinForm(){
        return "member/join";
    }

//    @PostMapping("/join")
//    public String join(){
//        return "redirect:/member/login";
//    }
}
