package com.taehi.springfirst.service;

import com.taehi.springfirst.dao.BoardDAO;
import com.taehi.springfirst.domain.BoardVO;
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
    public List<BoardVO> selectBoardList() {
        List<BoardVO> list = boardDAO.selectBoardList();
        return list;
    }
    @Override
    public BoardVO selectBoardById(int seq) {
        BoardVO boardVO= boardDAO.selectBoardById(seq);
        return boardVO;
    }
    @Override
    public int insertBoard(BoardVO boardVO) {
        return boardDAO.insertBoard(boardVO);
    }

    @Override
    public void updateBoard(BoardVO boardVO) {

    }

    @Override
    public void deleteBoard(int seq) {
        boardDAO.deleteBoard(seq);
    }


}
