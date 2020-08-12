package com.taehi.springfirst.dao;


import com.taehi.springfirst.domain.BoardVO;
import com.taehi.springfirst.paging.PagingVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class BoardDAOImpl extends JdbcDaoSupport implements BoardDAO {
    final String SELECT_ALL_SQL="select * from Hboard_TB order by h_id desc limit ? offset (? - 1) * ?";
    final String SELECT_ID_SQL="select * from Hboard_TB where h_id = ?";
    final String SELECT_SEQ_MAX = "select max(h_id) from Hboard_TB";
    final String SELECT_COUNT = "select count(*) from Hboard_TB";
    final String INSERT_SQL="insert into Hboard_TB(h_subject,h_content,h_user_name)\n" +
            "   \t\t\t\t values (?,?,?);\t";
    final String DELETE_ID_SQL="delete from Hboard_TB where h_id= ? ";
    final String UPDATE_SQL="update Hboard_TB set h_subject=?,h_content=?,h_user_name=? where h_id=?";
    public BoardDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public List<BoardVO> selectBoardList(PagingVO vo) {
//        List<Map<String,Object>> rows = getJdbcTemplate().queryForList(SELECT_ALL_SQL);
//        return rows.stream().map(row->{
//            BoardVO boardVO= new BoardVO();
//            boardVO.setId((String)row.get("h_id"));
//            boardVO.setSubject((String)row.get("h_subject"));
//            boardVO.setContent((String)row.get("h_content"));
//            boardVO.setCreated((Date)row.get("h_created"));
//            boardVO.setUserName((String)row.get("h_user_name"));
//            boardVO.setHit((int) row.get("h_hit"));
//            boardVO.setLike((int)row.get("h_like"));
//            return boardVO;
//        }).collect(Collectors.toList());
//
        return getJdbcTemplate().query(SELECT_ALL_SQL, BeanPropertyRowMapper.newInstance(BoardVO.class),vo.getCntPerPage(),vo.getNowPage(),vo.getCntPerPage());
        //레시피 480장 참조,  RowMapper하위클래스,  특정클래스의 새인스턴스로 자동매핑가능. 프로퍼티 언더스코어 추가컬럼까지 매핑가능.
    }
    @Override
    public BoardVO selectBoardById(int seq) {
        return getJdbcTemplate().queryForObject(SELECT_ID_SQL,BeanPropertyRowMapper.newInstance(BoardVO.class) ,seq);
    }

    @Override
    public int countBoard() {
        return getJdbcTemplate().queryForObject(SELECT_COUNT,Integer.class);
    }

    @Override
    @Transactional
    public int insertBoard(BoardVO boardVO) {
        int seq;
        getJdbcTemplate().update(INSERT_SQL,boardVO.getH_subject(),boardVO.getH_content(),boardVO.getH_userName());
        seq = getJdbcTemplate().queryForObject(SELECT_SEQ_MAX,Integer.class);
        return seq;
    }

    @Override
    public void updateBoard(BoardVO boardVO,int seq) {
        getJdbcTemplate().update(UPDATE_SQL,boardVO.getH_subject(),boardVO.getH_content(),boardVO.getH_userName(),seq);
    }

    @Override
    public void deleteBoard(int seq) {
        getJdbcTemplate().update(DELETE_ID_SQL, seq);
    }


}
