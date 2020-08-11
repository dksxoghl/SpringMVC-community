package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.BoardVO;

import java.util.List;

public interface BoardService {
    public List<BoardVO> selectBoardList() ;
    public int insertBoard(BoardVO boardVO,int seq);
    public void updateBoard(BoardVO boardVO);
    public void deleteBoard(int seq);
    public BoardVO selectBoardById(int seq);
}
