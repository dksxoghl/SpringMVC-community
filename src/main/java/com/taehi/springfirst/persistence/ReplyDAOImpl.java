package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyDAOImpl extends JdbcDaoSupport implements ReplyDAO {
    final String SELECT_ALL_SQL = "select * from reply_tb where hy_id=? order by re_group,re_order limit ? offset (? - 1) * ?";
    final String INSERT_SQL= "insert into reply_tb(re_id,hy_id,user_id,re_content,re_group)\n" +
            "values (nextval('seq_reply'),?,?,?,nextval('seq_reply'))";
    final String INSERT_SQL_RE= "insert into reply_tb(re_id,hy_id,user_id,re_content,re_group)\n" +
            "values (nextval('seq_reply'),?,?,?,?)";
    final String DELETE_ID_SQL="delete from reply_tb where re_id= ? ";
    final String SELECT_COUNT="select count(*) from reply_tb where hy_id=?";
    final String SELECT_ID_PARENT="select re_id from reply_tb where re_parent=? order by re_order desc";
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
    public int insertReReply(ReplyVO replyVO,int groupId) {
        System.out.println("실행?"); return 2;
//        return getJdbcTemplate().update(INSERT_SQL_RE,replyVO.getHyId(),replyVO.getUserId(),replyVO.getReContent(),groupId);
    }
    @Override
    public Optional<Integer> idFromParent(int parentId) {
        return getJdbcTemplate().queryForList(SELECT_ID_PARENT,Integer.class,parentId).stream().findFirst();
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
