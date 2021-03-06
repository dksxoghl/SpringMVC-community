package com.taehi.springfirst.persistence;


import com.taehi.springfirst.domain.board.BoardEntity;
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
    final String SELECT_ALL_SQL =
            "select Hboard_TB.hy_id,(ROW_NUMBER() OVER(order by Hboard_TB.hy_id)) AS hy_no,Hboard_TB.*,rep, hy_like" +
                    " from Hboard_TB left outer join (select count(*) as rep, hy_id from reply_tb group by hy_id) B " +
                    "on Hboard_TB.hy_id= B.hy_id" +
                    " left outer join (select count(*) as hy_like,hy_id from like_tb group by hy_id) C \n" +
                    "on Hboard_TB.hy_id= C.hy_id " +
                    " where category_id=(select category_id from category_tb where category_url=?) and not hboard_tb.is_admin " +
                    "order by Hboard_TB.hy_id desc limit ? offset (? - 1) * ?";

    final String SELECT_ALLBEST_SQL =       //일정치이상 추천수 게시글
            "select Hboard_TB.hy_id,(ROW_NUMBER() OVER(order by Hboard_TB.hy_id)) AS hy_no,Hboard_TB.*,rep, hy_like" +
                    " from Hboard_TB left outer join (select count(*) as rep, hy_id from reply_tb group by hy_id) B " +
                    "on Hboard_TB.hy_id= B.hy_id" +
                    " left outer join (select count(*) as hy_like,hy_id from like_tb group by hy_id) C \n" +
                    "on Hboard_TB.hy_id= C.hy_id " +
                    " where category_id=(select category_id from category_tb where category_url=?) and not hboard_tb.is_admin " +
                    "and hy_like>=? " +
                    "order by Hboard_TB.hy_id desc limit ? offset (? - 1) * ?";
    final String SELECT_NOTICE_ALL = "select (ROW_NUMBER() OVER(order by A.hy_id)) AS hy_no,A.*,rep,hy_like\n" +
            "from Hboard_TB A left outer join (select count(*) as rep, hy_id from reply_tb group by hy_id) B\n" +
            "                               on A.hy_id= B.hy_id\n" +
            "               left outer join (select count(*) as hy_like,hy_id from like_tb group by hy_id) C\n" +
            "                               on A.hy_id= C.hy_id\n" +
            "where category_id=(select category_id from category_tb where category_url=?) and A.is_admin";
    final String SELECT_ID_SQL = "select * from Hboard_TB where hy_id = ?";
    final String SELECT_SEQ_MAX = "select max(hy_id) from Hboard_TB";
    final String SELECT_COUNT = "select count(*) from Hboard_TB where category_id=(\n" +
            "\tselect category_id from category_tb where category_url=?) ";
    final String SELECT_BEST_COUNT = "select count(*) from (select Hboard_TB.hy_id, hy_like\n" +
            "                    from Hboard_TB \n" +
            "                     left outer join (select count(*) as hy_like,hy_id from like_tb group by hy_id) C \n" +
            "                   on Hboard_TB.hy_id= C.hy_id \n" +
            "                    where category_id=(select category_id from category_tb where category_url=?)\n" +
            "                    and hy_like>=? ) B";
    final String SELECT_CATEGORY_SQL = " select * from category_tb";
    final String SELECT_FIND_ID = "select category_id from category_tb where category_url=?";
    final String INSERT_SQL = "insert into Hboard_TB(category_id,hy_subject,hy_content,user_id,hy_url,is_admin)\n" +
            "   \t\t\t\t values (?,?,?,?,?,?);\t";
    final String DELETE_ID_SQL = "delete from Hboard_TB where hy_id= ? ";
    final String UPDATE_SQL = "update Hboard_TB set hy_subject=?,hy_content=?,user_id=? where hy_id=?";
    final String UPDATE_HIT_SQL = "update Hboard_TB set hy_hit=hy_hit+1 where hy_id=?";

    public BoardDAOImpl(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    public List<BoardEntity> selectBoardList(PagingVO vo, String url) {
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
        return getJdbcTemplate().query(SELECT_ALL_SQL, BeanPropertyRowMapper.newInstance(BoardEntity.class), url, vo.getCntPerPage(), vo.getNowPage(), vo.getCntPerPage());
        //레시피 480장 참조,  RowMapper하위클래스,  특정클래스의 새인스턴스로 자동매핑가능. 프로퍼티 언더스코어 추가컬럼까지 매핑가능.
    }

    @Override
    public List<BoardEntity> selectSearchList(PagingVO vo, String url, String searchTarget, String searchKeyword) {
        String likeKeyword = "%" + searchKeyword + "%";
        String target1 = searchTarget;
        String target2 = searchTarget;
        System.out.println(target1 + likeKeyword);
        if (searchTarget.equals("hyAll")) {
            target1 = "hy_subject";
            target2 = "hy_content";
        }
        String SELECT_SEARCH_SQL =
                "select Hboard_TB.hy_id,(ROW_NUMBER() OVER(order by Hboard_TB.hy_id)) AS hy_no,Hboard_TB.*,rep, hy_like" +
                        " from Hboard_TB left outer join (select count(*) as rep, hy_id from reply_tb group by hy_id) B " +
                        "on Hboard_TB.hy_id= B.hy_id" +
                        " left outer join (select count(*) as hy_like,hy_id from like_tb group by hy_id) C \n" +
                        "on Hboard_TB.hy_id= C.hy_id " +
                        " where category_id=(select category_id from category_tb where category_url=?) and not hboard_tb.is_admin " +
                        "and (" + target1 + " like ? or " + target2 + " like ?) order by Hboard_TB.hy_id desc limit ? offset (? - 1) * ?";
        return getJdbcTemplate().query(SELECT_SEARCH_SQL, BeanPropertyRowMapper.newInstance(BoardEntity.class),
                url, likeKeyword, likeKeyword, vo.getCntPerPage(), vo.getNowPage(), vo.getCntPerPage());
    }

    @Override
    public List<BoardEntity> selectBestBoardList(PagingVO vo, String url, int best) {
        return getJdbcTemplate().query(SELECT_ALLBEST_SQL, BeanPropertyRowMapper.newInstance(BoardEntity.class), url, best, vo.getCntPerPage(), vo.getNowPage(), vo.getCntPerPage());
    }

    @Override
    public List<BoardEntity> selectBestSearchList(PagingVO vo, String url, int best, String searchTarget, String searchKeyword) {
        String likeKeyword = "%" + searchKeyword + "%";
        String target1 = searchTarget;
        String target2 = searchTarget;
        System.out.println(target1 + likeKeyword);
        if (searchTarget.equals("hyAll")) {
            target1 = "hy_subject";
            target2 = "hy_content";
        }
        String SELECT_SEARCHBEST_SQL =       //일정치이상 추천수 게시글
                "select Hboard_TB.hy_id,(ROW_NUMBER() OVER(order by Hboard_TB.hy_id)) AS hy_no,Hboard_TB.*,rep, hy_like" +
                        " from Hboard_TB left outer join (select count(*) as rep, hy_id from reply_tb group by hy_id) B " +
                        "on Hboard_TB.hy_id= B.hy_id" +
                        " left outer join (select count(*) as hy_like,hy_id from like_tb group by hy_id) C \n" +
                        "on Hboard_TB.hy_id= C.hy_id " +
                        " where category_id=(select category_id from category_tb where category_url=?) and not hboard_tb.is_admin " +
                        "and hy_like>=? " +
                        "and (" + target1 + " like ? or " + target2 + " like ?)" +
                        "order by Hboard_TB.hy_id desc limit ? offset (? - 1) * ?";
        return getJdbcTemplate().query(SELECT_SEARCHBEST_SQL, BeanPropertyRowMapper.newInstance(BoardEntity.class),
                url, best, likeKeyword, likeKeyword, vo.getCntPerPage(), vo.getNowPage(), vo.getCntPerPage());
    }

    @Override
    public List<BoardEntity> selectNoticeList(String url) {
        return getJdbcTemplate().query(SELECT_NOTICE_ALL, BeanPropertyRowMapper.newInstance(BoardEntity.class), url);
    }


    @Override
    public BoardEntity selectBoardById(int seq) {
        return getJdbcTemplate().queryForObject(SELECT_ID_SQL, BeanPropertyRowMapper.newInstance(BoardEntity.class), seq);
    }

    @Override
    public int countBoard(String url) {
        return getJdbcTemplate().queryForObject(SELECT_COUNT, Integer.class, url);
    }

    @Override
    public int countSearchBoard(String url, String searchTarget, String searchKeyword) {
        String likeKeyword = "%" + searchKeyword + "%";
        String target1 = searchTarget;
        String target2 = searchTarget;
        System.out.println(target1 + likeKeyword);
        if (searchTarget.equals("hyAll")) {
            target1 = "hy_subject";
            target2 = "hy_content";
        }
        String SELECT_SEARCH_COUNT = "select count(*) from Hboard_TB where category_id=(\n" +
                "\tselect category_id from category_tb where category_url=?) " +
                "and (" + target1 + " like ? or " + target2 + " like ?) ";
        return getJdbcTemplate().queryForObject(SELECT_SEARCH_COUNT, Integer.class, url, likeKeyword, likeKeyword);
    }

    @Override
    public int countBestBoard(String url, int best) {
        return getJdbcTemplate().queryForObject(SELECT_BEST_COUNT, Integer.class, url, best);
    }

    @Override
    public int countSearchBestBoard(String url, int best, String searchTarget, String searchKeyword) {
        String likeKeyword = "%" + searchKeyword + "%";
        String target1 = searchTarget;
        String target2 = searchTarget;
        System.out.println(target1 + likeKeyword);
        if (searchTarget.equals("hyAll")) {
            target1 = "hy_subject";
            target2 = "hy_content";
        }
        String SELECT_SEARCHBEST_COUNT = "select count(*) from (select Hboard_TB.hy_id, hy_like\n" +
                "                    from Hboard_TB \n" +
                "                     left outer join (select count(*) as hy_like,hy_id from like_tb group by hy_id) C \n" +
                "                   on Hboard_TB.hy_id= C.hy_id \n" +
                "                    where category_id=(select category_id from category_tb where category_url=?)\n" +
                "                    and hy_like>=? " +
                "and (" + target1 + " like ? or " + target2 + " like ?)   ) B";
        return getJdbcTemplate().queryForObject(SELECT_SEARCHBEST_COUNT, Integer.class, url, best, likeKeyword, likeKeyword);
    }


    @Override
    @Transactional //auto increment 키생성 기다리기위해, 글생성후 detail페이지 바로가야함.
    public int insertBoard(BoardEntity boardEntity) {
        int seq;
        getJdbcTemplate().update(INSERT_SQL, boardEntity.getCategoryId(), boardEntity.getHySubject(), boardEntity.getHyContent(), boardEntity.getUserId(), "www.hy", boardEntity.getIsAdmin());
        seq = getJdbcTemplate().queryForObject(SELECT_SEQ_MAX, Integer.class);
        return seq;
    }

    @Override
    public void updateBoard(BoardEntity boardEntity, int seq) {
        getJdbcTemplate().update(UPDATE_SQL, boardEntity.getHySubject(), boardEntity.getHyContent(), boardEntity.getUserId(), seq);
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
        return getJdbcTemplate().queryForObject(SELECT_FIND_ID, Integer.class, url);
    }

    @Override
    public void boardHit(int seq) {
        getJdbcTemplate().update(UPDATE_HIT_SQL, seq);
    }

}
