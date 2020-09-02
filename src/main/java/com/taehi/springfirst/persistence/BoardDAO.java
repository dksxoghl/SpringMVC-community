package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.BoardVO;
import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.domain.paging.PagingVO;

import java.util.List;

public interface BoardDAO {
    public List<BoardVO> selectBoardList(PagingVO vo,String url) ;
    public List<BoardVO> selectBestBoardList(PagingVO vo,String url,int best) ;
    public int insertBoard(BoardVO boardVO);
    public void updateBoard(BoardVO boardVO,int seq);
    public void deleteBoard(int seq);
    public BoardVO selectBoardById(int seq);
    public int countBoard(String url);
    public List<CategoryVO> selectCategoryList();
    public int findCategory(String url);
    public void boardHit(int seq);
}
