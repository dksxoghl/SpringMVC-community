package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.board.BoardVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import com.taehi.springfirst.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String write(@ModelAttribute("boardVO")BoardVO boardVO){
//        int seqParam=0;
//        System.out.println("write"+boardVO.getH_id()+"seq"+seqParam);
        int seq = boardService.insertBoard(boardVO,boardVO.getH_id());
        return "redirect:/detail?seq="+seq;
//         @RequestParam(value = "seq",required = false)int seqParam
    }
    @RequestMapping(value = {"/detail"})
    public String boardDetail(Model model,@RequestParam("seq")int seq,
                              @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage){
        System.out.println("hydetail"+seq);
        BoardVO boardVO = boardService.selectBoardById(seq);
        model.addAttribute("board", boardVO);

        PagingVO vo = createPaging(nowPage, cntPerPage);
        List<BoardVO> list = boardService.selectBoardList(vo);
        model.addAttribute("paging", vo);
        model.addAttribute("list", list);

        return "boardDetail";
    }
    public PagingVO createPaging(String nowPage, String cntPerPage){
        int total = boardService.countBoard();
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "10";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "10";
        }
        return new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    }
    @RequestMapping(value = {"/", "/hy"})
    public String boardList( Model model, @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {
//        System.out.println("hy"+vo.getNowPage()+" "+vo.getCntPerPage()+" "+vo.getStartPage());

        PagingVO vo= createPaging(nowPage, cntPerPage);
        model.addAttribute("paging", vo);

        List<BoardVO> list = boardService.selectBoardList(vo);
        model.addAttribute("list", list);

        return "hyboardList";
    }
}