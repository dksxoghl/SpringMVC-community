package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ReplyDAOImpl extends JdbcDaoSupport implements ReplyDAO {
    final String SELECT_ALL_SQL = "select * from reply_tb where h_id=? limit ? offset (? - 1) * ?";
    final String INSERT_SQL= "insert into reply_tb(h_id,user_id,re_content)\n" +
            "values (?,?,?)";
    final String DELETE_ID_SQL="delete from reply_tb where re_id= ? ";
    final String SELECT_COUNT="select count(*) from reply_tb where h_id=?";
    public ReplyDAOImpl(DataSource dataSource){
        setDataSource(dataSource);
    }


    @Override
    public List<ReplyVO> list(int h_id, PagingVO vo) {
//        assert getJdbcTemplate() != null;
        return getJdbcTemplate().query(SELECT_ALL_SQL, BeanPropertyRowMapper.newInstance(ReplyVO.class),h_id,vo.getCntPerPage(),vo.getNowPage(),vo.getCntPerPage());
    }

    @Override
    public int insertReply(ReplyVO replyVO) {
        return getJdbcTemplate().update(INSERT_SQL,replyVO.getH_id(),replyVO.getUser_id(),replyVO.getRe_content());
    }

    @Override
    public void deleteReply(int re_id) {
        getJdbcTemplate().update(DELETE_ID_SQL, re_id);
    }

    @Override
    public int countReply(int h_id) {
        return getJdbcTemplate().queryForObject(SELECT_COUNT,Integer.class ,h_id);
    }
}
