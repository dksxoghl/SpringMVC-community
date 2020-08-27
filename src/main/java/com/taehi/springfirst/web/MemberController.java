package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.domain.member.MemberVO;
//import com.taehi.springfirst.service.MemberService;
import com.taehi.springfirst.service.BoardService;
import com.taehi.springfirst.service.MemberService;
import com.taehi.springfirst.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;
    private BoardService boardService;

    @GetMapping("/{url}/joinForm")
    public String joinForm(Model model,@PathVariable String url){
        System.out.println("joinForm");
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute(url);
        return "joinForm";
    }
    @GetMapping("/{url}/loginForm")
    public String loginForm(Model model,@PathVariable String url){
        System.out.println("loginForm");
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute(url);
        return "loginForm";
    }
    @PostMapping("/user/join")
    public String join(@ModelAttribute MemberVO memberVO) {
        System.out.println(memberVO.getEmail()+memberVO.getUserId()+memberVO.isAdult());
        memberVO.setAdult(memberVO.isAdult());
        memberService.joinUser(memberVO);
        return "redirect:/hy";
    }
//    @PostMapping("/user/login")
//    public String login(@ModelAttribute MemberVO memberVO) {
////        System.out.println(memberVO.getEmail()+memberVO.getUserId()+memberVO.isAdult());
////        memberVO.setAdult(memberVO.isAdult());
//
//        memberService.loadUserByUsername(memberVO.getUserId());
//        return "/";
//    }

}
