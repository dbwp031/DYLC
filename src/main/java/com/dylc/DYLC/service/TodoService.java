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
    public int getSize(){return getList().size(); } // TODO: 2023-05-16 : 사이즈의 크기를 알기 위해선 반드시 findAll을 할 수 밖에 없는가? 

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
    public Todo updateDoneByIdTo(final Long id, final boolean done) {
        Todo todo = getTodoById(id);
        todo.setDone(done);
        return this.todoRepository.save(todo);
    }
    public void updateDoneAll(final List<Long> doneTodoIds){
        List<Long> idList = getIdList();
        if (idList.size() == 0) {
            return;
        } else {
            for (Long e : doneTodoIds) {
                updateDoneByIdTo(e,true);
                idList.remove(e);
            }

            idList.forEach(e -> updateDoneByIdTo(e,false));
        }

    }
    public void deleteTodoById(final Long id) {
        todoRepository.deleteById(id);
    }
}
