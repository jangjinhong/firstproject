package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j  //로깅 기능을 위한 애노테이션
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "/articles/new";
    }

    @PostMapping("/articles/create")    // post형식으로 URL 연결
    public String createArticle(ArticleForm form) { // 전송받은 폼 데이터를 저장한 객체인 dto를 매개변수로 설정
        //<DTO를 DB에 저장하기>
        // 1. DTO -> Entity로
        Article article = form.toEntity();
        log.info(form.toString()); //System.out.println("article.toString() = " + article.toString());
        
        // 2. Repository 사용해서 Entity를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "";
    }

}
