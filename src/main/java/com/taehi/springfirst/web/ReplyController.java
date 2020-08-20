package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import com.taehi.springfirst.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/reply")
public class ReplyController {
    private ReplyService replyService;

    public ReplyController(ReplyService replyService){
        this.replyService=replyService;
    }

    @GetMapping(value = "/all/{hId}/{nowPage}")
    public ResponseEntity<Map<String,Object>> list(@PathVariable("hId")int h_id,@PathVariable("nowPage")String nowPage){
        int total= replyService.countReply(h_id);
        if (nowPage == null ) {
            nowPage = "1";
        }
        PagingVO vo = new PagingVO(total,Integer.parseInt(nowPage), 10);
        ResponseEntity<Map<String,Object>> entity=null; //map으로
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("replyVO",replyService.list(h_id,vo));
            map.put("replyPage",vo);
            entity= new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return entity;
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<String> insertReply(@RequestBody ReplyVO replyVO) {
        System.out.println(replyVO.getH_id()+replyVO.getRe_content()+replyVO.getUser_id());
        ResponseEntity<String> entity = null;
        try {
            replyService.insertReply(replyVO);
            entity = new ResponseEntity<>("regSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
    @DeleteMapping(value = "/{re_id}")
    public ResponseEntity<String> deleteReply(@PathVariable int re_id){
        ResponseEntity<String> entity=null;
        try{
            replyService.deleteReply(re_id);
            entity= new ResponseEntity<>("delSuccess",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            entity= new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
