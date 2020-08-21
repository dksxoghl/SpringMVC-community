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
    final String SELECT_ALL_SQL = "select * from reply_tb where hy_id=? limit ? offset (? - 1) * ?";
    final String INSERT_SQL= "insert into reply_tb(hy_id,user_id,re_content)\n" +
            "values (?,?,?)";
    final String DELETE_ID_SQL="delete from reply_tb where re_id= ? ";
    final String SELECT_COUNT="select count(*) from reply_tb where hy_id=?";
    public ReplyDAOImpl(DataSource dataSource){
        setDataSource(dataSource);
    }


    @Override
    public List<ReplyVO> list(int hyId, PagingVO vo) {
//        assert getJdbcTemplate() != null;
        return getJdbcTemplate().query(SELECT_ALL_SQL, BeanPropertyRowMapper.newInstance(ReplyVO.class),hyId,vo.getCntPerPage(),vo.getNowPage(),vo.getCntPerPage());
    }

    @Override
    public int insertReply(ReplyVO replyVO) {
        return getJdbcTemplate().update(INSERT_SQL,replyVO.getHyId(),replyVO.getUserId(),replyVO.getReContent());
    }

    @Override
    public void deleteReply(int reId) {
        getJdbcTemplate().update(DELETE_ID_SQL, reId);
    }

    @Override
    public int countReply(int hyId) {
        return getJdbcTemplate().queryForObject(SELECT_COUNT,Integer.class ,hyId);
    }
}
