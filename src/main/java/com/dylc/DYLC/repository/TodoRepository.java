package com.dylc.DYLC.repository;

import com.dylc.DYLC.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
