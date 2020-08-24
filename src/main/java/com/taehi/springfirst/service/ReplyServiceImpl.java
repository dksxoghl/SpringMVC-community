package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import com.taehi.springfirst.persistence.ReplyDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

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
    public int insertReply(ReplyVO replyVO,String groupId) {
        if(groupId=="0") return replyDAO.insertReply(replyVO);
        else{
            int pId= replyVO.getReParent();
            //패런트가진애 찾고 그 넘버에 패런트인에 찾고(반복)  그애의 오더+1 그리고 같은그룹 그오더 업데이트
                while(true){
                    replyDAO.idFromParent(pId).isPresent(); //무한반복으로 찾자
                }
            //Optional에 값이 없으면 orElse()의 인자로서 실행된 값이 반환되므로 실행한 의미가 있지만, Optional에 값이 있으면 orElse()의 인자로서 실행된 값이 무시되고 버려진다.
           // System.out.println(replyDAO.idFromParent(replyVO.getReParent()).orElse(replyDAO.insertReReply(replyVO,Integer.parseInt(groupId))));
            return 1;
//            return replyDAO.insertReReply(replyVO,Integer.parseInt(groupId));
        }
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
