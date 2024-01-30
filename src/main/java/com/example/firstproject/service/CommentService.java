package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired CommentRepository commentRepository;
    @Autowired ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        // 댓글 조회-> (엔티티->DTO) 반환 -> 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. 게시글 조회, 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" + "대상 게시글이 존재하지 않습니다."));

        // 2. 부모 게시글에 새 엔티티 생성
        Comment comment = Comment.createComment(dto, article);

        // 3. 댓글 엔티티 -> DB에 저장
        Comment created = commentRepository.save(comment);

        // 4. entity -> dto로 변환하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없음"));

        // 2. 댓글 수정
        target.patch(dto);

        // 3. DB에 갱신
        Comment saved = commentRepository.save(target);

        // 4. 해당 Entity -> dto로 변환하여 리턴
        return CommentDto.createCommentDto(saved);
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
