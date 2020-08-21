package com.taehi.springfirst.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class ReplyVO {
    private int reId;
    private int hyId;
    private String userId;
    private Date reRegdate;
    private String reContent;

    public ReplyVO(){}

    public ReplyVO(int reId, int hyId, String userId, Date reRegdate, String reContent) {
        this.reId = reId;
        this.hyId = hyId;
        this.userId = userId;
        this.reRegdate = reRegdate;
        this.reContent = reContent;
    }
}
