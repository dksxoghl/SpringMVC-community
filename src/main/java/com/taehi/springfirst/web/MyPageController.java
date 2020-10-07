package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@Controller
public class MyPageController {
    private BoardService boardService;

    @GetMapping("/{url}/myPage")
    public String myPage(Model model, @PathVariable String url) {
        System.out.println("loginForm");
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute(url);
        model.addAttribute("deleteNav", "deleteNav");  // 헤더 소개없애기위해
        return "myPage";
    }
}
