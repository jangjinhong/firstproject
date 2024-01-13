package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    @Id //primary key
    @GeneratedValue // 자동 생성 기능 추가 (숫자가 자동으로 입력됨)
    private Long id;
    @Column // 필드 선언. (DB의 title 열과 연결)
    private String title;
    @Column
    private String content;
}
