package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


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
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id: " + id);
        // 1. id 조회해서 갖고오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 반환
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 목록 가져와서
        List<Article> articleEntityList = (List<Article>) articleRepository.findAll();
        // 2. 모델에 등록해서
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰에다 뿌리기
        return "/articles/index";
    }

}
