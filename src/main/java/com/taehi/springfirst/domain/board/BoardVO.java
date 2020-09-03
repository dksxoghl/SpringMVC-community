package com.taehi.springfirst.domain.board;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardVO {
    private int hyId;
    private int hyNo;
    private int categoryId;
    private String hySubject;
    private String hyContent;
    private String hyCreatedDate; // String hyCreatedDate; 현재: 시분 이전: 년월일시분
    private String userId;
    private int hyHit;
    private String hyLike;
    private String hyUrl;
    private String fileName;
    private String hyImg;
    private String rep; //댓글개수
    private Boolean isAdmin;
}
