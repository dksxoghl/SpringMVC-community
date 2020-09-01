package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.LikeVO;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LikeDAOImpl extends JdbcDaoSupport implements LikeDAO{
   final String SELECT_LIKE_SQL="select count(*) from like_tb where hy_id=? and user_id=?";

    public LikeDAOImpl(DataSource dataSource){
        setDataSource(dataSource);
    }

    @Override
    public int insertLike(LikeVO likeVO) {
        return 0;
    }

    @Override
    public int getLike(int hyId, String userId) {
        return getJdbcTemplate().queryForObject(SELECT_LIKE_SQL,Integer.class ,hyId,userId);
    }
}
