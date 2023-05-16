package com.dylc.DYLC;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoForm {
    @NotEmpty(message = "content는 필수항목입니다.")
    @Size(max = 50)
    private String content;
}
