package kr.boot.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/servlet")
    public String hello(){
        return "basic";
    }
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }
}
