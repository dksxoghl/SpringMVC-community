package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;

import java.util.List;

public interface ReplyDAO {
    public List<ReplyVO> list(int h_id, PagingVO vo);
    public int insertReply(ReplyVO replyVO);
    public void deleteReply(int re_id);
    public int countReply(int h_id);
}
