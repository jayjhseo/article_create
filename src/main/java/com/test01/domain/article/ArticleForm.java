package com.test01.domain.article;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {
    @NotEmpty(message = "제목은 필수사항")
    @Size(max = 200)
    private String title;
    @NotEmpty(message = "내용은 필수사항")
    private String content;
}
