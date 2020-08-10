package com.taehi.springfirst.dao;

import com.taehi.springfirst.domain.BoardVO;

import java.util.List;

public interface BoardDAO {
    public List<BoardVO> selectBoardList() ;
    public void insertBoard(BoardVO boardVO);
    public void updateBoard(BoardVO boardVO);
    public void deleteBoard(BoardVO boardVO);
    public BoardVO selectBoardById(String seq);
}
