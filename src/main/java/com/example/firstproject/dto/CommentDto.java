package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 보여줄 데이터를 담음. 반환 JSON 데이터 전송
@AllArgsConstructor
@NoArgsConstructor
@Getter @ToString
public class CommentDto {
//    @JsonProperty("article_id")
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    /*
    * 정적 메서드
    *   - 객체 생성 없이 호출가능한 메서드
    *   - 이러한 방식으로 객체를 생성하는 메서드를 "생성 메서드"라고 한다.
     */
    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
