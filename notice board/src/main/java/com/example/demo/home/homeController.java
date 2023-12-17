package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/login")
    public String loginform(){return "login";}
    @GetMapping("/join")
    public String joinform(){return "join";}
    @GetMapping("/board/paging")
    public String board(){
        return "paging";
    }

}
