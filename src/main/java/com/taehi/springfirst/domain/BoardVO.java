package com.taehi.springfirst.domain;

import java.util.Date;

public class BoardVO {
    private String id;
    private String subject;
    private String content;
    private Date created;
    private String userName;
    private int hit;
    private int like;

    public BoardVO() {
    }

    public BoardVO(String id, String subject, String content, Date created, String userName, int hit, int like) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.created = created;
        this.userName = userName;
        this.hit = hit;
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
