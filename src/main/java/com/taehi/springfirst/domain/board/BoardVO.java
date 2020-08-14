package com.taehi.springfirst.domain.board;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardVO {
    private int h_id;
    private int category_id;
    private String h_subject;
    private String h_content;
    private Date h_created;
    private String user_id;
    private int h_hit;
    private int h_like;
    private String h_url;
    private String file_name;
    private String h_img;
    public BoardVO() {
    }
    public BoardVO(int h_id, int category_id, String h_subject, String h_content, Date h_created, String user_id, int h_hit, int h_like, String h_url, String file_name, String h_img) {
        this.h_id = h_id;
        this.category_id = category_id;
        this.h_subject = h_subject;
        this.h_content = h_content;
        this.h_created = h_created;
        this.user_id = user_id;
        this.h_hit = h_hit;
        this.h_like = h_like;
        this.h_url = h_url;
        this.file_name = file_name;
        this.h_img = h_img;
    }
}
