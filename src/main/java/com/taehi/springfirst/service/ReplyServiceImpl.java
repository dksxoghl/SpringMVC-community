package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import com.taehi.springfirst.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{
    private ReplyDAO replyDAO;
    public ReplyServiceImpl(ReplyDAO replyDAO){
        this.replyDAO=replyDAO;
    }
    @Override
    public List<ReplyVO> list(int hyId, PagingVO vo) {
        return replyDAO.list(hyId,vo);
    }

    @Override
    public int insertReply(ReplyVO replyVO) {
        return replyDAO.insertReply(replyVO);
    }

    @Override
    public void deleteReply(int reId) {
        replyDAO.deleteReply(reId);
    }

    @Override
    public int countReply(int hyId) {
        return replyDAO.countReply(hyId);
    }
}
