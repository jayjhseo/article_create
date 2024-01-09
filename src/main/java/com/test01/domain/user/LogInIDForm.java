package com.test01.domain.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInIDForm {
    @NotEmpty(message = "ID는 필수로 입력해주세요")
    private String username;
    @NotEmpty(message = "비번 입력필수")
    private String password;

}
