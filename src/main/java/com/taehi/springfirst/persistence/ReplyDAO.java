package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.ReplyVO;

import java.util.List;

public interface ReplyDAO {
    public List<ReplyVO> list(int h_id);
    public int insertReply(ReplyVO replyVO);
    public void deleteReply(int re_id);
}
