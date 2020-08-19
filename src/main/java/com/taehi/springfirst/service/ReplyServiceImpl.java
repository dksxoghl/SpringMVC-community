package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.board.ReplyVO;
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
    public List<ReplyVO> list(int h_id) {
        return replyDAO.list(h_id);
    }

    @Override
    public int insertReply(ReplyVO replyVO) {
        return replyDAO.insertReply(replyVO);
    }

    @Override
    public void deleteReply(int re_id) {
        replyDAO.deleteReply(re_id);
    }
}
