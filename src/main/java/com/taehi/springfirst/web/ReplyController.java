package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import com.taehi.springfirst.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/reply")
public class ReplyController {
    private ReplyService replyService;

    //답글 현재요청페이지로 페이징처리하여 list 리턴
    @GetMapping(value = "/all/{hyId}/{nowPage}")
    public ResponseEntity<Map<String,Object>> list(@PathVariable("hyId")int hyId,@PathVariable("nowPage")String nowPage){
        int total= replyService.countReply(hyId);
//        if (nowPage == null ) {
//            nowPage = "1";
//        }
        PagingVO vo = new PagingVO(total,Integer.parseInt(nowPage), 10);
        ResponseEntity<Map<String,Object>> entity=null; //map으로
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("replyVO",replyService.list(hyId,vo));
            map.put("replyPage",vo);
            entity= new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return entity;
    }
    //페이지의 제일최신글을 먼저보기위해 마지막페이지 리턴
    @GetMapping(value = "/lastPage/{hyId}")
    public ResponseEntity<Integer> list(@PathVariable("hyId")int hyId){
        int total= replyService.countReply(hyId);
        PagingVO vo = new PagingVO(total,1, 10);
        ResponseEntity<Integer> entity=null; //map으로
        try {
            entity= new ResponseEntity<>(vo.getLastPage(), HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return entity;
    }
    @PostMapping(value = "/insert/{groupId}")
    public ResponseEntity<String> insertReply(@RequestBody ReplyVO replyVO, @PathVariable String groupId) {
        System.out.println("insertReply"+groupId);
        ResponseEntity<String> entity = null;
        try {
            replyService.insertReply(replyVO,groupId);
            entity = new ResponseEntity<>("regSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
    @DeleteMapping(value = "/delete/{reId}")
    public ResponseEntity<String> deleteReply(@PathVariable int reId){
        ResponseEntity<String> entity=null;
        try{
            replyService.deleteReply(reId);
            entity= new ResponseEntity<>("delSuccess",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            entity= new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
