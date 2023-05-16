package com.dylc.DYLC.service;

import com.dylc.DYLC.entity.Todo;
import com.dylc.DYLC.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getList() {
        return this.todoRepository.findAll();
    }
    public List<Long> getIdList(){
        List<Long> idList = new ArrayList<>();
        List<Todo> todoList = getList();
        todoList.forEach(todo -> idList.add(todo.getId()));

        return idList;
    }
    public Todo getTodoById(final Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no such data"));
    }

    public void create(final String content) {
        if(content == null) throw new IllegalArgumentException("todo item can not be null");
        Todo todo = new Todo();
        todo.setContent(content);
        todo.setDone(false);
        todo.setCreateDate(LocalDateTime.now());
        this.todoRepository.save(todo);
    }

    public Todo updateTodoById(final Long id, final Todo updateTodo) {
        Todo todo = getTodoById(id);
        todo.setContent(updateTodo.getContent());
        todo.setDone(updateTodo.isDone());

        return this.todoRepository.save(todo);
    }

    public void deleteTodoById(final Long id) {
        todoRepository.deleteById(id);
    }
}
