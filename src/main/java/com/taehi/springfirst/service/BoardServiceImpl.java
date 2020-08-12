package com.taehi.springfirst.service;

import com.taehi.springfirst.dao.BoardDAO;
import com.taehi.springfirst.domain.BoardVO;
import com.taehi.springfirst.paging.PagingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<BoardVO> selectBoardList(PagingVO vo) {
        return boardDAO.selectBoardList(vo);
    }
    @Override
    public BoardVO selectBoardById(int seq) {
        return boardDAO.selectBoardById(seq);
    }

    @Override
    public int countBoard() {
        return boardDAO.countBoard();
    }

    @Override
    public int insertBoard(BoardVO boardVO,int seq) {
        int retValue;
// seq 따라 update,insert
        if(seq==0) retValue= boardDAO.insertBoard(boardVO);
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


}
