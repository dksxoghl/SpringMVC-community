package com.taehi.springfirst.web;

import com.taehi.springfirst.domain.board.LikeVO;
import com.taehi.springfirst.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/like")
public class LikeController {

    private LikeService likeService;

    @GetMapping(value = "/{userId}/{hyId}")
    public ResponseEntity<Integer> getLike(@PathVariable("hyId")int hyId,@PathVariable("userId")String userId){
        System.out.println("getLike");
        ResponseEntity<Integer> entity=null;
        int likeVal= likeService.getLike(hyId,userId);
        try {
            entity= new ResponseEntity<>(likeVal, HttpStatus.OK);
        }catch (Exception e){
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        return entity;
    }
    @PostMapping
    public ResponseEntity<String> insertLike(@RequestBody LikeVO likeVO) {
        System.out.println("insertLike");
        ResponseEntity<String> entity = null;
        try {
            entity = new ResponseEntity<>("regSuccess", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
}
