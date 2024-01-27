package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @ToString
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor // 매개변수없는 디폴트 생성자 자동 생성
public class Comment {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 자동으로 1씩 증가
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

}
