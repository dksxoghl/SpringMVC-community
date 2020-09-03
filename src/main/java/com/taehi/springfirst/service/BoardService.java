package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.board.BoardEntity;
import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.domain.paging.PagingVO;

import java.util.List;

public interface BoardService {
    public List<BoardEntity> selectBoardList(PagingVO vo, String url, int best) ;
    public int insertBoard(BoardEntity boardEntity, int seq, String url);
    public void deleteBoard(int seq);
    public BoardEntity selectBoardById(int seq);
    public int countBoard(String url,int best);
    public List<CategoryVO> selectCategoryList();

}
