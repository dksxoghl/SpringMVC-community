package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.BoardVO;
import com.taehi.springfirst.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class ExamController {

    private BoardService boardService;

    @Autowired
    public ExamController(BoardService boardService) {
        this.boardService = boardService;
    }

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

//    @RequestMapping(method = RequestMethod.GET, value = {"/","/welcome"})
//    public String welcome(Model model) {
//        System.out.println("welcome");
//        Date today = new Date();
//        model.addAttribute("today", today);
//        return "welcome";
//    }
    @RequestMapping(value={"/","/hy"})
    public String boardList(Model model) throws Exception{
        System.out.println("welcome");
        List<BoardVO> list = boardService.selectBoardList();

        model.addAttribute("list", list);

        return "hyboardList";
    }
}