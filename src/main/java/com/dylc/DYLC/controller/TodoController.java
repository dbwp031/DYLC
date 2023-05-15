package com.dylc.DYLC.controller;

import com.dylc.DYLC.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequiredArgsConstructor
@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;
    @GetMapping("")
    public String todoList() {
        return "/todo/main";
    }

    @GetMapping("/create") //TODO: 홈페이지에서 요청하기 누르면 -> /TODO/CREATE GET 요청 -> /TODO/CREATE 페이지 생성해서 반환
    public String todoNewBoard() {
        return "/todo/create";
    }
    @PostMapping("/create")
    public String todo(@RequestParam String todoContents) {
//        System.out.println("todoContents: "+ todoContents);
        todoService
        return "redirect:/todo";
    }


}
