package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyController {
    private ReplyService replyService;

    public ReplyController(ReplyService replyService){
        this.replyService=replyService;
    }
    @GetMapping(value = "/reply/all/{hId}")
    public ResponseEntity<List<ReplyVO>> list(@PathVariable("hId")int h_id){
        ResponseEntity<List<ReplyVO>> entity=null;
        try {
            entity= new ResponseEntity<>(replyService.list(h_id), HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return entity;
    }

}
