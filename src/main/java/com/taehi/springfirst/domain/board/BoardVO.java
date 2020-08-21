package com.taehi.springfirst.domain.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BoardVO {
    private int hyId;
    private int hyNo;
    private int categoryId;
    private String hySubject;
    private String hyContent;
    private Date hyCreatedDate;
    private String userId;
    private int hyHit;
    private int hyLike;
    private String hyUrl;
    private String fileName;
    private String hyImg;
    private String rep; //댓글개수
    public BoardVO() {
    }


    public BoardVO(int hyId, int hyNo, int categoryId, String hySubject, String hyContent, Date hyCreatedDate, String userId, int hyHit, int hyLike, String hyUrl, String fileName, String hyImg, String rep) {
        this.hyId = hyId;
        this.hyNo = hyNo;
        this.categoryId = categoryId;
        this.hySubject = hySubject;
        this.hyContent = hyContent;
        this.hyCreatedDate = hyCreatedDate;
        this.userId = userId;
        this.hyHit = hyHit;
        this.hyLike = hyLike;
        this.hyUrl = hyUrl;
        this.fileName = fileName;
        this.hyImg = hyImg;
        this.rep = rep;
    }
}
