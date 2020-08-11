package com.taehi.springfirst.domain;

import java.util.Date;

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

    public int getH_id() {
        return h_id;
    }

    public void setH_id(int h_id) {
        this.h_id = h_id;
    }

    public String getH_subject() {
        return h_subject;
    }

    public void setH_subject(String h_subject) {
        this.h_subject = h_subject;
    }

    public String getH_content() {
        return h_content;
    }

    public void setH_content(String h_content) {
        this.h_content = h_content;
    }

    public Date getH_created() {
        return h_created;
    }

    public void setH_created(Date h_created) {
        this.h_created = h_created;
    }

    public String getH_userName() {
        return h_userName;
    }

    public void setH_userName(String h_userName) {
        this.h_userName = h_userName;
    }

    public int getH_hit() {
        return h_hit;
    }

    public void setH_hit(int h_hit) {
        this.h_hit = h_hit;
    }

    public int getH_like() {
        return h_like;
    }

    public void setH_like(int h_like) {
        this.h_like = h_like;
    }
}
