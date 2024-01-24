package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @ToString
public class Article {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 기능 추가 (숫자가 자동으로 입력됨)
    private Long id;
    @Column // 필드 선언. (DB의 title 열과 연결)
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if(article.getTitle() != null)  // 갱신할 값이 없으면 기존 데이터 사용
            this.title = article.title;
        if(article.getContent() != null) // 즉,  !! null이 아니면 갱신. null이면 기존꺼 사용 !!
            this.content = article.content;
    }
}
