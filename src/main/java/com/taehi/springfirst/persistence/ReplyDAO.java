package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;

import java.util.List;
import java.util.Optional;

public interface ReplyDAO {
    public List<ReplyVO> list(int hyId, PagingVO vo);
    public int insertReply(ReplyVO replyVO);
    public int insertReReply(ReplyVO replyVO,int groupId);
    public void deleteReply(int reId);
    public int countReply(int hyId);
    public Optional<Integer> idFromParent(int parentId);


}
