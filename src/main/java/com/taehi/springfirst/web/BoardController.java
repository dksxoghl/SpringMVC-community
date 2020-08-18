package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.board.BoardVO;
import com.taehi.springfirst.domain.category.CategoryVO;
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

    @RequestMapping(value = {"/"})
    public String redirect(){
        return "redirect:/hy";
    }

    @RequestMapping(value = {"/{url}/deleteForm"})
    public String deleteForm(Model model,@RequestParam("seq")int seq,@PathVariable String url){
        System.out.println("deleteForm");
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("seq", seq);
        model.addAttribute(url);
        return "boardDelete";
    }
    @RequestMapping(value = {"{url}/delete"})
    public String delete(@RequestParam("seq")int seq,@PathVariable String url){
        System.out.println("delete");
        boardService.deleteBoard(seq);
        return "redirect:/"+url;
    }
    @RequestMapping(value = {"/{url}/writeForm"})
//    ,@RequestParam(value = "seq",required = false) int seq
    public String writeForm(Model model,@ModelAttribute("boardVO")BoardVO boardVO,@PathVariable String url){
        System.out.println("writeForm"+boardVO.getH_created_date() +" "+ boardVO.getH_id());
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("board",boardVO);
        model.addAttribute(url);
        return "boardWrite";
    }
    @RequestMapping(value = {"/{url}/write"})
    public String write(@ModelAttribute("boardVO")BoardVO boardVO,@PathVariable String url){
//        System.out.println("write"+boardVO.getH_id()+"gethid cate"+boardVO.getCategory_id());
        int seq = boardService.insertBoard(boardVO,boardVO.getH_id(),url);
        return "redirect:/"+url+"/detail/"+seq;
    }
    @RequestMapping(value = {"/{url}/detail/{seq}"})
    public String boardDetail(Model model,@PathVariable("seq")int seq,
                              @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage,
                              @PathVariable(required = false) String url){
        System.out.println("hydetail"+seq);
        BoardVO boardVO = boardService.selectBoardById(seq);
        model.addAttribute("board", boardVO);

        PagingVO vo = createPaging(nowPage, cntPerPage,url);
        List<BoardVO> list = boardService.selectBoardList(vo,url);
        model.addAttribute("paging", vo);
        model.addAttribute("list", list);
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);

        model.addAttribute("seeingNow",seq); //현재보고있는글표시
        return "boardDetail";
    }
    public PagingVO createPaging(String nowPage, String cntPerPage,String url){
        int total = boardService.countBoard(url);
        if (nowPage == null && cntPerPage == null) {
            nowPage = "1";
            cntPerPage = "20";
        } else if (nowPage == null) {
            nowPage = "1";
        } else if (cntPerPage == null) {
            cntPerPage = "20";
        }
        return new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
    }
    @RequestMapping(value = {"/{url}"})
    public String boardList( Model model, @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage,@PathVariable String url)  {
//        System.out.println("hy"+vo.getNowPage()+" "+vo.getCntPerPage()+" "+vo.getStartPage());
        System.out.println(url+"과연");
        if(url==null) url="hy";
        model.addAttribute(url);

        PagingVO vo= createPaging(nowPage, cntPerPage,url);
        model.addAttribute("paging", vo);

        List<BoardVO> list = boardService.selectBoardList(vo,url);
        model.addAttribute("list", list);
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);

        return "index";
    }
}