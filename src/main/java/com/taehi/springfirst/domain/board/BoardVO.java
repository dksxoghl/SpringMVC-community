package com.taehi.springfirst.domain.board;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
    private int hyId;
    private int hyNo;
    private int categoryId;
    private String hySubject;
    private String hyContent;
    private Date hyCreatedDate;
    private String userId;
    private int hyHit;
    private String hyLike;
    private String hyUrl;
    private String fileName;
    private String hyImg;
    private String rep; //댓글개수

}
