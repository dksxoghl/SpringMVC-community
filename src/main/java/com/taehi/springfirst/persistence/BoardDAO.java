package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.BoardEntity;
import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.domain.paging.PagingVO;

import java.util.List;

public interface BoardDAO {
    public List<BoardEntity> selectBoardList(PagingVO vo, String url);

    public List<BoardEntity> selectSearchList(PagingVO vo, String url, String searchTarget, String searchKeyword);

    public List<BoardEntity> selectBestBoardList(PagingVO vo, String url, int best);

    public List<BoardEntity> selectBestSearchList(PagingVO vo, String url, int best, String searchTarget, String searchKeyword);

    public List<BoardEntity> selectNoticeList(String url);

    public int insertBoard(BoardEntity boardEntity);

    public void updateBoard(BoardEntity boardEntity, int seq);

    public void deleteBoard(int seq);

    public BoardEntity selectBoardById(int seq);

    public int countBoard(String url);

    public int countSearchBoard(String url, String searchTarget, String searchKeyword);

    public int countBestBoard(String url, int best);

    public int countSearchBestBoard(String url, int best, String searchTarget, String searchKeyword);

    public List<CategoryVO> selectCategoryList();

    public int findCategory(String url);

    public void boardHit(int seq);
}
