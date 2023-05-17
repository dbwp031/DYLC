package com.dylc.DYLC.service;

import com.dylc.DYLC.entity.Todo;
import com.dylc.DYLC.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class TodoServiceTest {
    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;
    @Test
    public void id_리스트를_모두_반환한다(){
        //given
        Todo todo1 = new Todo();
        todo1.setContent("content1");
        todo1.setCreateDate(LocalDateTime.now());
        todo1.setDone(true);
        todoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setContent("content2");
        todo2.setCreateDate(LocalDateTime.now());
        todo2.setDone(false);
        todoRepository.save(todo2);

        //when
        List<Long> idList = todoService.getIdList();

        //then
        List<Todo> list = todoService.getList();
        assertThat(idList.get(0)).isEqualTo(list.get(0).getId());
        assertThat(idList.get(1)).isEqualTo(list.get(1).getId());

    }

    @Test
    public void done의_참거짓값이_입력된_값으로_변경된다() {
        //given
        Todo todo1 = new Todo();
        todo1.setContent("content1");
        todo1.setCreateDate(LocalDateTime.now());
        todo1.setDone(true);
        todoRepository.save(todo1);
        //when
        todoService.updateDoneByIdTo(todo1.getId(), false);
        //then
        assertThat(todoRepository.findById(todo1.getId()).get().isDone()).isEqualTo(false);
    }

    @Test
    public void 입력값을_기반으로_모든TODO의_done값이_변경된다() {
        //given
        Todo todo1 = new Todo();
        todo1.setContent("content1");
        todo1.setCreateDate(LocalDateTime.now());
        todo1.setDone(false);
        todoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setContent("content2");
        todo2.setCreateDate(LocalDateTime.now());
        todo2.setDone(false);
        todoRepository.save(todo2);

        List<Long> doneTodoIds = new ArrayList<>();
        doneTodoIds.add(todo1.getId());

        //when
        todoService.updateDoneAll(doneTodoIds);

        //then
        assertThat(todo1.isDone()).isEqualTo(true);
        assertThat(todo2.isDone()).isEqualTo(false);

    }
    @Test
    public void 입력값이_없으면_모두_false가_나와야한다() {
        //given
        Todo todo1 = new Todo();
        todo1.setContent("content1");
        todo1.setCreateDate(LocalDateTime.now());
        todo1.setDone(false);
        todoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setContent("content2");
        todo2.setCreateDate(LocalDateTime.now());
        todo2.setDone(false);
        todoRepository.save(todo2);

        List<Long> doneTodoIds = new ArrayList<>();
        doneTodoIds.add(todo1.getId());

        //when
        todoService.updateDoneAll(doneTodoIds);

        //then
        assertThat(todo1.isDone()).isEqualTo(true);
        assertThat(todo2.isDone()).isEqualTo(false);

    }
}