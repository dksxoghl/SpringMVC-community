package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import com.taehi.springfirst.persistence.ReplyDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO;

    public ReplyServiceImpl(ReplyDAO replyDAO) {
        this.replyDAO = replyDAO;
    }

    @Override
    public List<ReplyVO> list(int hyId, PagingVO vo) {
        return replyDAO.list(hyId, vo);
    }


    //부모인애들중 제일아래있는애 찾고 그 넘버가 부모인 넘버또찾고(반복) 찾으면 그애의 오더+1 후 같은그룹의 오더 쭉 업데이트 후 인서트. 트랜잭션처리
    @Override
    @Transactional
    public int insertReply(ReplyVO replyVO) {
        int groupId= replyVO.getReGroup();
        if (groupId==0) return replyDAO.insertReply(replyVO);
        else {
            int pId = replyVO.getReParent();
            int count = 1;
            Optional<Integer> reNo = replyDAO.idFromParent(pId);
            while (count < 5) {
                if (!reNo.isPresent()) {  //반복으로 찾자
                    break;
                }
                count++;
                pId = reNo.get();
                reNo = replyDAO.idFromParent(pId);
            }
            int reOrder = 0;
            if (count == 1) {
                reOrder = replyVO.getReOrder();
            } else {
                reOrder = replyDAO.findOrder(pId);
            }
            System.out.println(reOrder + "그애의 오더 플러스1전 ");
            replyVO.setReOrder(++reOrder);
            replyDAO.updateOrder(groupId, reOrder);
            //Optional에 값이 없으면 orElse()의 인자로서 실행된 값이 반환되므로 실행한 의미가 있지만, Optional에 값이 있으면 orElse()의 인자로서 실행된 값이 무시되고 버려진다.
            // System.out.println(replyDAO.idFromParent(replyVO.getReParent()).orElse(replyDAO.insertReReply(replyVO,Integer.parseInt(groupId))));

            return replyDAO.insertReReply(replyVO, groupId);
        }
    }

    //있으면 [작성자가 삭제한 댓글입니다.] update 없으면 딜리트 그대로
    @Override
    public void deleteReply(int reId) {
        Optional<Integer> reNo = replyDAO.idFromParent(reId);
        if(reNo.isPresent()) {
            replyDAO.updateDelete(reId);
        } else {
            replyDAO.deleteReply(reId);
        }
    }

    @Override
    public int countReply(int hyId) {
        return replyDAO.countReply(hyId);
    }
}
