package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.domain.member.MemberVO;
//import com.taehi.springfirst.service.MemberService;
import com.taehi.springfirst.service.BoardService;
import com.taehi.springfirst.service.MemberService;
import com.taehi.springfirst.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//    @PostMapping("/{url}/logOut")
//    public String logOut() {
//        System.out.println("logout");
//        return "redirect:/hy";
//    }
    @PostMapping("/user/join")
    public String join(@ModelAttribute MemberVO memberVO) {
        System.out.println(memberVO.getEmail()+memberVO.getUserId()+memberVO.isAdult());
        memberVO.setAdult(memberVO.isAdult());
        memberService.joinUser(memberVO);
        return "redirect:/hy";
    }
    //기본 제공해주는 SecurityContextLogoutHandler의 logout을 사용해서 로그아웃 처리
    @GetMapping(value = "/{url}/logOut")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
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
