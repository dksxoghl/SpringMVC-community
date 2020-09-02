package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.board.LikeVO;
import com.taehi.springfirst.persistence.LikeDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService{
    private LikeDAO likeDAO;

    @Override
    public int insertLike(LikeVO likeVO) {
        return likeDAO.insertLike(likeVO);
    }

    @Override
    public int getLike(int hyId, String userId) {
        return likeDAO.getLike(hyId,userId);
    }
}
