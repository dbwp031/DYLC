package com.dylc.DYLC.repository;

import com.dylc.DYLC.entity.Todo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TodoRepository extends JpaRepository<Todo, Long> {

}
