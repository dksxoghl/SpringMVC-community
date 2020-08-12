package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.BoardVO;
import com.taehi.springfirst.paging.PagingVO;

import java.util.List;

public interface BoardService {
    public List<BoardVO> selectBoardList(PagingVO vo) ;
    public int insertBoard(BoardVO boardVO,int seq);
    public void deleteBoard(int seq);
    public BoardVO selectBoardById(int seq);
    public int countBoard();
}
