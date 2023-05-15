package com.dylc.DYLC;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("")
    public String mainController() {
        System.out.println("hi");
        return "redirect:/todo";
    }
}
