package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.board.LikeVO;

public interface LikeService {
    public int insertLike(LikeVO likeVO);

    public int getLike(int hyId, String userId);
}
