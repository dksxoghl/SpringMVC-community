package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.member.MemberVO;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberDAOImpl extends JdbcDaoSupport implements MemberDAO {
    final String JOIN_SQL = "insert into user_tb(user_id,password,adult,email) values (?,?,?,?)";
    final String FIND_USER_SQL = "select * from user_tb where user_id=?";

    public MemberDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public int joinUser(MemberVO memberVO) {
        return getJdbcTemplate().update(JOIN_SQL, memberVO.getUserId(), memberVO.getPassword(), memberVO.isAdult(), memberVO.getEmail());
    }

    @Override
    public Optional<MemberVO> findByUserId(String userId) {
//  MemberVO memberVO = ;
        return Optional.ofNullable(DataAccessUtils.singleResult(getJdbcTemplate().query(FIND_USER_SQL, BeanPropertyRowMapper.newInstance(MemberVO.class), userId)));
//        return Optional.ofNullable(getJdbcTemplate().queryForObject(FIND_USER_SQL,  BeanPropertyRowMapper.newInstance(MemberVO.class),userId));
    }
//    private class A implements RowMapper<Optional<MemberVO>>{
//
//        @Override
//        public Optional<MemberVO> mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return Optional.empty();
//        }
//    }
}
