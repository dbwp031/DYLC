package com.dylc.DYLC.service;

import com.dylc.DYLC.entity.Todo;
import com.dylc.DYLC.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getList() {
        return this.todoRepository.findAll();
    }
}
