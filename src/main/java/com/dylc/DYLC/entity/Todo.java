package com.dylc.DYLC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter @Setter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="content", nullable=false, length=50)
    private String content;
    @Column(name="done", nullable=false)
    private boolean done;
    @Column(name="createDate", nullable = false )
    private LocalDateTime createDate;

}
