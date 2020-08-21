package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;

import java.util.List;

public interface ReplyDAO {
    public List<ReplyVO> list(int hyId, PagingVO vo);
    public int insertReply(ReplyVO replyVO);
    public void deleteReply(int reId);
    public int countReply(int hyId);
}
