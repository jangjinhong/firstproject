package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

// DTO: 폼 데이터를 받을 그릇
@AllArgsConstructor
@ToString
public class MemberForm {
    // DTO 필드 명이 뷰의 name 속성값과 일치해야 함
    private Long id;
    private String email;
    private String password;

    public Member toEntity() {
        return new Member(id, email, password);
    }
}
