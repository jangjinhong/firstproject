package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ArticleServiceTest {
    @Autowired private ArticleService articleService;

    @Test
    void index() {
        // 1. 예상 데이터
        List<Article> articles = articleService.index();
        Article a = new Article(1L, "제목1", "내용1");
        Article b = new Article(2L, "제목2", "내용2");
        Article c = new Article(3L, "제목3", "내용3");
        List<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));

        // 2. 실제 데이터


        // 3. 비교 및 검증
        assertEquals(expected.toString(), articles.toString());
    }


    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "제목1", "내용1");

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;

        // 2. 실제 데이터
        Article show = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected, show);
    }

    @Test
    @Transactional
    void create_성공_title_과_content_만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);    //db에서 id 자동 생성
        Article expected = new Article(4L, title, content);
        System.out.println("expected.toString() = " + expected.toString());

        // 2. 실제 데이터
        Article article = articleService.create(dto);
        System.out.println("article.toString() = " + article.toString());

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 4L;
        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        Long id = 1L;
        String title = "title수정";
        String content = "content수정";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);

        Article update = articleService.update(id, dto);

        assertEquals(update.toString(), expected.toString());
    }

    @Test
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        Long id = 1L;
        String title = "title";
        ArticleForm dto = new ArticleForm(id, title, null);
        Article expected = new Article(id, "title", "내용1");

        Article update = articleService.update(id, dto);

        assertEquals(update.toString(), expected.toString());
    }

    @Test
    void update_실패_존재하지_않는_id의_dto_입력() {
        Long id = -1L;
        ArticleForm dto = new ArticleForm(id, "title수정", "content수정");
        Article article = null;

        Article update = articleService.update(id, dto);
        assertEquals(update, article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력() {
        Long id = 1L;
        Article article = articleService.show(id);

        Article deleted = articleService.delete(id);

        assertEquals(deleted, article);
    }

    @Test
    void delete_실패_존재하지_않는_id_입력() {
        Article article = null;

        Article deleted = articleService.delete(-1L);

        assertEquals(article, deleted);
    }
}