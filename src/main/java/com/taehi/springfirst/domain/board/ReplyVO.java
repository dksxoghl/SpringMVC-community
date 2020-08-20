package com.taehi.springfirst.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class ReplyVO {
    private int re_id;
    private int h_id;
    private String user_id;
    private Date re_regdate;
    private String re_content;

    public ReplyVO(){}
    public ReplyVO(int re_id, int h_id, String user_id, Date re_regdate, String re_content) {
        this.re_id = re_id;
        this.h_id = h_id;
        this.user_id = user_id;
        this.re_regdate = re_regdate;
        this.re_content = re_content;
    }
}
