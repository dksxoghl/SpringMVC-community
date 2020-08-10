package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.BoardVO;

import java.util.List;

public interface BoardService {
    public List<BoardVO> selectBoardList() ;
    public void insertBoard(BoardVO boardVO);
    public void updateBoard(BoardVO boardVO);
    public void deleteBoard(BoardVO boardVO);
    public BoardVO selectBoardByCode(BoardVO boardVO);
}
