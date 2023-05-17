package com.dylc.DYLC.controller;


import com.dylc.DYLC.TodoForm;
import com.dylc.DYLC.entity.Todo;
import com.dylc.DYLC.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;
    @GetMapping("/list")
    public String list(Model model){
        List<Todo> todoList = this.todoService.getList();                                                                                                                    
        model.addAttribute("todoList", todoList);
        return "/todo/list";
    }
    @GetMapping("/create")
    public String todoCreate(TodoForm todoForm){
        return "/todo/create";
    }

    @PostMapping("/create")
    public String todoCreate(@Valid TodoForm todoForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "redirect:/todo/create";
        }
        this.todoService.create(todoForm.getContent());
        System.out.println("create!!!");
        return "/todo/list";
    }

    @GetMapping("/check-done")
    public String todoListUpdate(@RequestParam(value="todos",required = false) Long[] todos){
        System.out.println("check-done!!!!");
        List<Long> doneTodoIds = new ArrayList<>();

        if (todos != null) {
            for (Long e : todos) {
                doneTodoIds.add(e);
            }
        }
        this.todoService.updateDoneAll(doneTodoIds);
        return "redirect:/todo/list";
    }

    @GetMapping("")
    public String todoList() {
        return "/todo/main";
    }

//    @GetMapping("/create") //TODO: 홈페이지에서 요청하기 누르면 -> /TODO/CREATE GET 요청 -> /TODO/CREATE 페이지 생성해서 반환
//    public String todoNewBoard() {
//        return "/todo/create";
//    }
//    @PostMapping("/create")
//    public String todo(@RequestParam String todoContents) {
////        System.out.println("todoContents: "+ todoContents);
////        todoService
//        return "redirect:/todo";
//    }


}
