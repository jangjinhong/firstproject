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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        // 3. 결과 리다이렉트
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 뿌리기
        model.addAttribute("article", articleEntity);
        return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        // <from data -> DB에 반영>
        // 1. DTO -> Entity
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 2. repository를 통해 Entity -> DB에 저장
        Article data = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(data != null) {
            articleRepository.save(articleEntity);
        }
        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attribute) {
        log.info("delete 요청 들어옴!!");
        // 1. 삭제할 데이터 갖고오기
        Article deleteEntity = articleRepository.findById(id).orElse(null);
        // 2. DB에서 삭제
        if(deleteEntity.getId() != null) {
            articleRepository.delete(deleteEntity);
            attribute.addFlashAttribute("msg", "삭제완료!");
        }
        log.info("삭제 대상: " + deleteEntity.toString());
        // 3. 리다이렉트
        return "redirect:/articles";
    }

}
