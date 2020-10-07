package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.board.LikeVO;

import java.util.Optional;

public interface LikeDAO {
    public int insertLike(LikeVO likeVO);

    public int getLike(int hyId, String userId);
}
