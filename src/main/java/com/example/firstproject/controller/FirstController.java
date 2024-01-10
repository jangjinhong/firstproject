package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetyou(Model model) {
        model.addAttribute("username", "장지농");
        return "greetings"; //머스테치 파일 명
    }

    @GetMapping("/bye")     // 1. 요청
    public String seeYouNext(Model model) { // 2. 해당 요청은 이 메소드 실행, 3. Model 객체 가져오기
        model.addAttribute("username", "장지농");  // 4. 모델 변수 등록
        return "goodbye";       // 5. 요청에 따른 머스테치 반환 -> 웹 브라우저로 전송
    }
}
