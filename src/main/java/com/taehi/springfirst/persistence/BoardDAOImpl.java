package com.taehi.springfirst.persistence;


import com.taehi.springfirst.domain.board.BoardVO;
import com.taehi.springfirst.domain.category.CategoryVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BoardDAOImpl extends JdbcDaoSupport implements BoardDAO {
    final String SELECT_ALL_SQL="select (ROW_NUMBER() OVER(order by h_id)) AS h_no\n" +
            "     ,* from Hboard_TB " +
            "where category_id=(\n" +
            "\tselect category_id from category_tb where category_url=?)"+
            "order by h_id desc limit ? offset (? - 1) * ?";
    final String SELECT_ID_SQL="select * from Hboard_TB where h_id = ?";
    final String SELECT_SEQ_MAX = "select max(h_id) from Hboard_TB";
    final String SELECT_COUNT = "select count(*) from Hboard_TB where category_id=(\n" +
            "\tselect category_id from category_tb where category_url=?) ";
    final String SELECT_CATEGORY_SQL = " select * from category_tb";
    final String SELECT_FIND_ID="select category_id from category_tb where category_url=?";
    final String INSERT_SQL="insert into Hboard_TB(category_id,h_subject,h_content,user_id,h_url)\n" +
            "   \t\t\t\t values (?,?,?,?,?);\t";
    final String DELETE_ID_SQL="delete from Hboard_TB where h_id= ? ";
    final String UPDATE_SQL="update Hboard_TB set h_subject=?,h_content=?,user_id=? where h_id=?";
    final String UPDATE_HIT_SQL="update Hboard_TB set h_hit=h_hit+1 where h_id=?";
    public BoardDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public List<BoardVO> selectBoardList(PagingVO vo,String url) {
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
        return getJdbcTemplate().query(SELECT_ALL_SQL, BeanPropertyRowMapper.newInstance(BoardVO.class),url,vo.getCntPerPage(),vo.getNowPage(),vo.getCntPerPage());
        //레시피 480장 참조,  RowMapper하위클래스,  특정클래스의 새인스턴스로 자동매핑가능. 프로퍼티 언더스코어 추가컬럼까지 매핑가능.
    }
    @Override
    public BoardVO selectBoardById(int seq) {
        return getJdbcTemplate().queryForObject(SELECT_ID_SQL,BeanPropertyRowMapper.newInstance(BoardVO.class) ,seq);
    }

    @Override
    public int countBoard(String url) {
        return getJdbcTemplate().queryForObject(SELECT_COUNT,Integer.class,url);
    }

    @Override
    @Transactional //auto increment 키생성 기다리기위해, 글생성후 detail페이지 바로가야함.
    public int insertBoard(BoardVO boardVO) {
        int seq;
        getJdbcTemplate().update(INSERT_SQL,boardVO.getCategory_id(),boardVO.getH_subject(),boardVO.getH_content(),boardVO.getUser_id(),"www.hy");
        seq = getJdbcTemplate().queryForObject(SELECT_SEQ_MAX,Integer.class);
        return seq;
    }

    @Override
    public void updateBoard(BoardVO boardVO,int seq) {
        getJdbcTemplate().update(UPDATE_SQL,boardVO.getH_subject(),boardVO.getH_content(),boardVO.getUser_id(),seq);
    }

    @Override
    public void deleteBoard(int seq) {
        getJdbcTemplate().update(DELETE_ID_SQL, seq);
    }

    @Override
    public List<CategoryVO> selectCategoryList() {
        return getJdbcTemplate().query(SELECT_CATEGORY_SQL, BeanPropertyRowMapper.newInstance(CategoryVO.class));
    }

    @Override
    public int findCategory(String url) {
        return getJdbcTemplate().queryForObject(SELECT_FIND_ID,Integer.class,url);
    }

    @Override
    public void boardHit(int seq) {
        getJdbcTemplate().update(UPDATE_HIT_SQL,seq);
    }

}
