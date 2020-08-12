package com.taehi.springfirst.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class BoardVO {
    private int h_id;
    private String h_subject;
    private String h_content;
    private Date h_created;
    private String h_userName;
    private int h_hit;
    private int h_like;

    public BoardVO() {
    }

    public BoardVO(int h_id, String h_subject, String h_content, Date h_created, String h_userName, int h_hit, int h_like) {
        this.h_id = h_id;
        this.h_subject = h_subject;
        this.h_content = h_content;
        this.h_created = h_created;
        this.h_userName = h_userName;
        this.h_hit = h_hit;
        this.h_like = h_like;
    }

}
