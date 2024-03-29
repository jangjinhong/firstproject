package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String newMemberForm() {
        return "/members/new";
    }

    @PostMapping("/join")
    public String createMember(MemberForm form) {
        //1. DTO -> Entity로 반환
        Member member = form.toEntity();
        log.info(member.toString());
        //2. 리포지토리를 이용하여 Entity를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();    //redirect는 컨트롤러 메소드를 한 번 더 실행해야 하는 꼴
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "/members/show"; //GetMapping의 url에서 처리한 값을 받을 뷰
    }

    @GetMapping("/members")
    public String index(Model model) {
        List<Member> memberList = (List<Member>) memberRepository.findAll();
        model.addAttribute("members", memberList);
        return "/members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("members", member);
        return "/members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) {
        Member memberEntity = form.toEntity();
        Member member = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(member.getId() != null)
            memberRepository.save(memberEntity);
        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Member deleteEntity = memberRepository.findById(id).orElse(null);
        if(deleteEntity.getId() != null) {
            memberRepository.delete(deleteEntity);
            rttr.addFlashAttribute("msg", "데이터 삭제 완료!");
        }
        return "redirect:/members";
    }
}
