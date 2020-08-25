package com.taehi.springfirst;

import com.taehi.springfirst.config.Config;
import com.taehi.springfirst.domain.board.ReplyVO;
import com.taehi.springfirst.domain.paging.PagingVO;
import com.taehi.springfirst.persistence.ReplyDAO;
import com.taehi.springfirst.persistence.ReplyDAOImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

//@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Config.class, ReplyDAOImpl.class})
public class ReplyDAOTest {
//    private static final Logger logger  = LoggerFactory.getLogger(ReplyDAOTest.class);

    @Autowired
    private ReplyDAO replyDAO;

    @Test
    public void testList(){
      replyDAO.list(138,new PagingVO(3,1,7)).stream().forEach(s-> System.out.println(s.getReContent()));
    }
    @Test
    public void testInsertReply(){
        for (int i = 0; i < 3; i++) {
            ReplyVO replyVO=new ReplyVO();
            replyVO.setHyId(138);
            replyVO.setUserId("ㅌㅎ");
            replyVO.setReContent("aaa");
            replyDAO.insertReply(replyVO);
        }
    }
    @Test
    public void testDeleteReply(){
        replyDAO.deleteReply(1);
    }
}
