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
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

//    @ResponseBody
//    @GetMapping("/hello")
//    public String hello() {
//        return "HelloWorld";
//    }



    @RequestMapping(value = {"/deleteForm"})
    public String deleteForm(Model model,@RequestParam("seq")int seq){
        System.out.println("deleteForm");
        model.addAttribute("seq", seq);
        return "boardDelete";
    }
    @RequestMapping(value = {"/delete"})
    public String delete(@RequestParam("seq")int seq){
        System.out.println("delete");
        boardService.deleteBoard(seq);
        return "redirect:/hy";
    }
    @RequestMapping(value = {"/writeForm"})
//    ,@RequestParam(value = "seq",required = false) int seq
    public String writeForm(Model model,@ModelAttribute("boardVO")BoardVO boardVO){
        System.out.println("writeForm"+boardVO.getH_subject() +" "+ boardVO.getH_id());
        model.addAttribute("board",boardVO);
        return "boardWrite";
    }
    @RequestMapping(value = {"/write"})
    public String write(Model model,@ModelAttribute("boardVO")BoardVO boardVO, @RequestParam("seq")int seqParam){
        System.out.println("write"+boardVO.getH_id()+"seq"+seqParam);
        int seq = boardService.insertBoard(boardVO,seqParam);
        return "redirect:/detail?seq="+seq;
    }
    @RequestMapping(value = {"/detail"})
    public String boardDetail(Model model,@RequestParam("seq")int seq){
        System.out.println("hydetail"+seq);
        BoardVO boardVO = boardService.selectBoardById(seq);
        model.addAttribute("board", boardVO);

        List<BoardVO> list = boardService.selectBoardList();
        model.addAttribute("list", list);

        return "boardDetail";
    }

    @RequestMapping(value = {"/", "/hy"})
    public String boardList(Model model) throws Exception {
        System.out.println("hy");
        List<BoardVO> list = boardService.selectBoardList();
        model.addAttribute("list", list);
        return "hyboardList";
    }
}