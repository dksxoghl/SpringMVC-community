package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.board.ReplyVO;

import java.util.List;

public interface ReplyService {
    public List<ReplyVO> list(int h_id);
    public int insertReply(ReplyVO replyVO);
    public void deleteReply(int re_id);
}
