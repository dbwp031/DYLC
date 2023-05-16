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
            return "/todo/create";
        }
        this.todoService.create(todoForm.getContent());
        System.out.println("create!!!");
        return "redirect:/todo/list";
    }

    @PostMapping("/check-done")
    public String todoListUpdate(@RequestParam(value="todos",required = false) Long[] todos
                                 ){
        if (todos != null) {
            List<Long> idList = todoService.getIdList();
            for (Long todoId: todos){
                if(!this.todoService.getTodoById(todoId).isDone()){
                    Todo targetTodo = this.todoService.getTodoById(todoId);
                    System.out.println("{} is Done"+todoId);
                    Todo tempTodo = new Todo();
                    tempTodo.setId(targetTodo.getId());
                    tempTodo.setContent(targetTodo.getContent());
                    tempTodo.setDone(true);
                    tempTodo.setCreateDate(targetTodo.getCreateDate());
                    this.todoService.updateTodoById(todoId, tempTodo);
                }
                idList.remove(todoId);
            }
            for(Long todoId : idList) {
                Todo targetTodo = this.todoService.getTodoById(todoId);
                System.out.println("{} is Done"+todoId);
                Todo tempTodo = new Todo();
                tempTodo.setId(targetTodo.getId());
                tempTodo.setContent(targetTodo.getContent());
                tempTodo.setDone(false);
                tempTodo.setCreateDate(targetTodo.getCreateDate());
                this.todoService.updateTodoById(todoId, tempTodo);
            }
        }
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
