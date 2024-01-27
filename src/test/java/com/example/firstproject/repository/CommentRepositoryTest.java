package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class CommentRepositoryTest {
    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 4번 게시글의 모든 댓글 조회
        * 1. 입력 데이터 준비
        * 2. 실제 데이터
        * 3. 예상 데이터
        * 4. 비교 및 검증 */
        Long id = 4L;
        Article article = new Article(id, "당신의 인생 영화는?", "댓글 고");
        Comment comment1 = new Comment(1L, article, "닉네임41", "짱구극장판");
        Comment comment2 = new Comment(2L, article, "닉네임42", "극한직업");
        Comment comment3 = new Comment(3L, article, "닉네임43", "상견니영화재밋는데");

        List<Comment> expected = Arrays.asList(comment1, comment2, comment3);

        List<Comment> comments = commentRepository.findByArticleId(id);

        assertEquals(expected.toString(), comments.toString(), "4번 게시글의 모든 댓글 출력");
    }
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId2() {

        /* Case 2: 1번 게시글의 모든 댓글 조회 */
        Long id2 = 1L;
        List<Comment> comments2 = commentRepository.findByArticleId(id2);

        Article article2 = new Article(id2, "제목1", "내용1");
        List<Comment> expected2 = Arrays.asList();

        assertEquals(expected2.toString(), comments2.toString(), "1번 글은 댓글 없음");
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // 1. 실제 데이터
        String nickname = "닉네임41";
        List<Comment> comments = commentRepository.findByNickname(nickname);

        // 2. 예상 데이터
        Comment c1 = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글 고"), nickname, "짱구극장판");
        Comment c2 = new Comment(4L, new Article(5L, "주술회전", "댓글 고고"), nickname, "주술회전재밋음");
        Comment c3 = new Comment(7L, new Article(6L, "라이즈맨날싱글만냄서운해", "댓글 고고고"), nickname, "ㅇㅈ;;");
        List<Comment> expected = Arrays.asList(c1, c2, c3);

        // 3. 비교 및 검증
        assertEquals(comments.toString(), expected.toString(), "nickname이 쓴 댓글 모두 출력");
    }

    @Test
    void findByArticleId_존재하지_않는_게시글의_댓글_조회() {
        Long id = 9L;

        List<Comment> comments = commentRepository.findByArticleId(id);

        List<Comment> expected = Arrays.asList();

        assertEquals(expected, comments);
    }

    @Test
    void findByNickname_닉네임이_null인_경우() {
        String nickname = null;
        List<Comment> comments = commentRepository.findByNickname(nickname);

        List<Comment> expected =Arrays.asList();

        assertEquals(expected, comments);
    }

    @Test
    void findByNickname_닉네임이_없을_경우() {
        String nickname = "";
        List<Comment> comments = commentRepository.findByNickname(nickname);

        List<Comment> expected = Arrays.asList();

        assertEquals(expected, comments);
    }

}