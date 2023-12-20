package com.pro06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String loginForm(){
        return "member/login";
    }

    @GetMapping("/term")
    public String Term(){
        return "member/term";
    }
}
