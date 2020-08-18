package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.persistence.BoardDAO;
import com.taehi.springfirst.domain.board.BoardVO;
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
    public List<BoardVO> selectBoardList(PagingVO vo,String url) {
        return boardDAO.selectBoardList(vo,url);
    }
    @Override
    @Transactional
    public BoardVO selectBoardById(int seq) {
        boardDAO.boardHit(seq);
        return boardDAO.selectBoardById(seq);
    }

    @Override
    public int countBoard(String url) {
        return boardDAO.countBoard(url);
    }


    @Override
    public int insertBoard(BoardVO boardVO,int seq, String url) {
        int retValue;
// seq 따라 update,insert
        if(seq==0) {
            boardVO.setCategory_id(boardDAO.findCategory(url));
            retValue = boardDAO.insertBoard(boardVO);
        }
        else {
            boardDAO.updateBoard(boardVO,seq);
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
