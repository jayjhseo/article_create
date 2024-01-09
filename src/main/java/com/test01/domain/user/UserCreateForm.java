package com.test01.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @Email
    @NotEmpty(message = "유저이름은 필수 내용")
    private String username;
    @NotEmpty(message = "닉네임은 필수 내용")
    @Size(min = 3, max = 12)
    private String nickname;
    @NotEmpty(message = "비밀번호는 필수 내용")
    private String password;
    @NotEmpty(message = "비밀번호 확인은 필수 내용")
    private String password2;
}
