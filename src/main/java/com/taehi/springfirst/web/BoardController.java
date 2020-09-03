package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.board.BoardEntity;
import com.taehi.springfirst.domain.board.BoardVO;
import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import com.taehi.springfirst.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class BoardController {

    private BoardService boardService;

//    @Autowired
//    public BoardController(BoardService boardService) {
//        this.boardService = boardService;
//    }
    @RequestMapping(value = {"/"})
    public String redirect(){
        return "redirect:/hy";
    }

    @RequestMapping(value = {"/{url}/deleteForm/{seq}"})
    public String deleteForm(Model model,@PathVariable int seq,@PathVariable String url){
        System.out.println("deleteForm");
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("seq", seq);
        model.addAttribute(url);
        return "boardDelete";
    }
    @RequestMapping(value = {"{url}/delete/{seq}"})
    public String delete(@PathVariable int seq,@PathVariable String url){
        System.out.println("delete");
        boardService.deleteBoard(seq);
        return "redirect:/"+url;
    }
    @RequestMapping(value = {"/{url}/writeForm"})
//    ,@RequestParam(value = "seq",required = false) int seq
    public String writeForm(Model model, @ModelAttribute("boardVO") BoardEntity boardEntity, @PathVariable String url){
        System.out.println("writeForm"+ boardEntity.getHyCreatedDate() +" "+ boardEntity.getHyId());
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("board", boardEntity);
        model.addAttribute(url);
        return "boardWrite";
    }
    @RequestMapping(value = {"/{url}/write"})
    public String write(@ModelAttribute("boardVO") BoardEntity boardEntity, @PathVariable String url){
        System.out.println("write"+ boardEntity.getHyId()+"gethid "+ boardEntity.getIsAdmin());
        int seq = boardService.insertBoard(boardEntity, boardEntity.getHyId(),url);
        return "redirect:/"+url+"/detail/"+seq;         //쓰기 후 바로 디테일페이지 이동
    }
    @RequestMapping(value = {"/{url}/detail/{seq}"})
    public String boardDetail(Model model,@PathVariable("seq")int seq,  //게시글번호
                              @RequestParam(value="nowPage", required=false)String nowPage  //페이징
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage,
                              @RequestParam(value="best", required=false)String best ,  //추천글만보기 분류
                              @PathVariable(required = false) String url){
        System.out.println("hydetail"+seq);
        if(best==null||best.equals("")) best="0";
        model.addAttribute("best",best);

        BoardEntity boardEntity = boardService.selectBoardById(seq);
        model.addAttribute("board", boardEntity);

        PagingVO vo = createPaging(nowPage, cntPerPage,url,Integer.parseInt(best));
        model.addAttribute("paging", vo);

        List<BoardEntity> list = boardService.selectBoardList(vo,url,Integer.parseInt(best));
        model.addAttribute("list",  changeDate(list));
        List<BoardEntity> ntList= boardService.selectNoticeList(url);

        model.addAttribute("ntList",  changeDate(ntList));

        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);

        model.addAttribute("seeingNow",seq); //현재보고있는글표시
        model.addAttribute("detail","detail"); //글보기페이지 댓글플로팅버튼때문
        return "boardDetail";
    }
    private PagingVO createPaging(String nowPage, String cntPerPage,String url,int best){     //디테일, 리스트 페이지 공통페이기객체생성기능
        int total = boardService.countBoard(url,best);
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
    private List<BoardVO> changeDate(List<BoardEntity> list){
        Date date=new Date();
        DateFormat sfday = new SimpleDateFormat("YYYY-MM-dd");
        DateFormat sfmin = new SimpleDateFormat("HH:mm");
        List<BoardVO> boardVOList= list.stream().map(be -> {
            String hyCreateDate = be.getHyCreatedDate().toString().substring(0, 10).equals(sfday.format(date))?
                    sfmin.format(be.getHyCreatedDate()) : sfday.format(be.getHyCreatedDate()).substring(5,10);
            return BoardVO.builder()
                    .hyId(be.getHyId()).hyNo(be.getHyNo()).categoryId(be.getCategoryId()).hySubject(be.getHySubject())
                    .hyContent(be.getHyContent()).hyCreatedDate(hyCreateDate).userId(be.getUserId()).hyHit(be.getHyHit())
                    .hyLike(be.getHyLike()).hyUrl(be.getHyUrl()).fileName(be.getFileName()).hyImg(be.getHyImg()).rep(be.getRep()).isAdmin(be.getIsAdmin())
                    .build();
        }).collect(Collectors.toList());
        return boardVOList;
    }
    @RequestMapping(value = {"/{url}"})
    public String boardList( Model model, @RequestParam(value="nowPage", required=false)String nowPage
            , @RequestParam(value="cntPerPage", required=false)String cntPerPage,@RequestParam(value="best", required=false)String best ,@PathVariable String url)  {
//        System.out.println("hy"+vo.getNowPage()+" "+vo.getCntPerPage()+" "+vo.getStartPage());
        System.out.println(url+"과연"+best);
//        if(url==null) url="hy";
        if(best==null||best.equals("")) best="0";

        model.addAttribute(url);
        model.addAttribute("best",best);

        PagingVO vo= createPaging(nowPage, cntPerPage,url,Integer.parseInt(best));
        model.addAttribute("paging", vo);

        List<BoardEntity> list = boardService.selectBoardList(vo,url,Integer.parseInt(best));
        model.addAttribute("list",  changeDate(list));

        List<BoardEntity> ntList= boardService.selectNoticeList(url);
        model.addAttribute("ntList",  changeDate(ntList));
        /*List<String> li= list.stream().map(BoardEntity::getHyCreatedDate).map(l-> {
            if (l.toString().substring(0, 10).equals(sfday.format(date))) {
                return sfmin.format(l);
            } else {
                return sfday.format(l).substring(5,10);
            }
        }).collect(Collectors.toList());*/
//        li.stream().forEach(l-> System.out.println(l));
//        model.addAttribute("date",li);
//       List<Date> li= list.stream().map(l->{
//
//                if (l.getHyCreatedDate().toString().substring(0, 10).equals(sfday.format(date))) {
//                  return  l.setHyCreatedDate(new Date(sfday.format(l.getHyCreatedDate())));
//                } else {
//                   return  l.setHyCreatedDate(new Date(sfmin.format(l.getHyCreatedDate())));
//                }
//
////            return date;
//        }).collect(Collectors.toList());
        List<CategoryVO> categoryList = boardService.selectCategoryList();
        model.addAttribute("categoryList",categoryList);

        return "index";
    }

}