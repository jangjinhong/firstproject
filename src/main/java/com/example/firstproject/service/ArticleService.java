package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired private ArticleRepository articleRepository;

    public List<Article> index() {
        return (List<Article>)articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        // 1. dto -> entity 변환
        Article article = dto.toEntity();
        if(article.getId() != null) return null;
        // 2. repository를 이용해 entity -> db에 저장
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. dto -> entity 변환
        Article article = dto.toEntity();
        // 2. 변경할 애 누구임
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청
        if(target == null || id != target.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        // 4. 변경
        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null) return null;
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. entity "묶음" -> dto "묶음"으로 변환
        List<Article> articleList = dtos.stream()
                                    .map(dto -> dto.toEntity())
                                    .collect(Collectors.toList());
        // 2. db 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 3. 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패!"));

        // 4. 결과 값 반환
        return articleList;
    }
}
