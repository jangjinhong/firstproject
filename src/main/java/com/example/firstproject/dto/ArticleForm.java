package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// 폼에 입력된 데이터를 전달 받을 그릇
@AllArgsConstructor
@ToString
public class ArticleForm {
    // dto 필드명과 뷰의 name 속성값이 일치해야 한다
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
