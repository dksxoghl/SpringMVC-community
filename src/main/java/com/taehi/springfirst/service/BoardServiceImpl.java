package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.persistence.BoardDAO;
import com.taehi.springfirst.domain.board.BoardEntity;
import com.taehi.springfirst.domain.paging.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<BoardEntity> selectBoardList(PagingVO vo, String url, int best) { //추천수0 or 이상으로 개념글판별
        if(best==0){
            return boardDAO.selectBoardList(vo,url);
        }
        return boardDAO.selectBestBoardList(vo,url,best);
    }
    @Override
    @Transactional
    public BoardEntity selectBoardById(int seq) {   //상세보기시 조회수 증가먼저.
        boardDAO.boardHit(seq);
        return boardDAO.selectBoardById(seq);
    }

    @Override
    public int countBoard(String url,int best) { //게시글,베스트게시글 카운트
        if(best==0)return boardDAO.countBoard(url);
        else return boardDAO.countBestBoard(url,best);
    }


    @Override
    public int insertBoard(BoardEntity boardEntity, int seq, String url) {
        int retValue;   //삽입, 업데이트 한 후 바로 그글띄우기 위함.
// seq 따라 update,insert
        if(seq==0) {
            boardEntity.setCategoryId(boardDAO.findCategory(url));
            retValue = boardDAO.insertBoard(boardEntity);
        }
        else {
            boardDAO.updateBoard(boardEntity,seq);
            retValue = seq;
        }
        return retValue;
    }
    @Override
    public void deleteBoard(int seq) {
        boardDAO.deleteBoard(seq);
    }

    @Override
    public List<CategoryVO> selectCategoryList() {
        return boardDAO.selectCategoryList();
    }
}
