package com.taehi.springfirst.dao;


import com.taehi.springfirst.domain.BoardVO;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class BoardDAOImpl extends JdbcDaoSupport implements BoardDAO {
    final String SELECT_ALL_SQL="select * from Hboard_TB";

    public BoardDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public List<BoardVO> selectBoardList() {
        List<Map<String,Object>> rows = getJdbcTemplate().queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row->{
            BoardVO boardVO= new BoardVO();
            boardVO.setId((String)row.get("h_id"));
            boardVO.setSubject((String)row.get("h_subject"));
            boardVO.setContent((String)row.get("h_content"));
            boardVO.setCreated((Date)row.get("h_created"));
            boardVO.setUserName((String)row.get("h_user_name"));
            boardVO.setHit((int) row.get("h_hit"));
            boardVO.setLike((int)row.get("h_like"));
            return boardVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void insertBoard(BoardVO boardVO) {

    }

    @Override
    public void updateBoard(BoardVO boardVO) {

    }

    @Override
    public void deleteBoard(BoardVO boardVO) {

    }

    @Override
    public BoardVO selectBoardByCode(BoardVO boardVO) {
        return null;
    }
}
