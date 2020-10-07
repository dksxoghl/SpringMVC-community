package com.taehi.springfirst.persistence;

import com.taehi.springfirst.domain.member.MemberVO;

import java.util.Optional;

public interface MemberDAO {
    public int joinUser(MemberVO memberVO);

    public Optional<MemberVO> findByUserId(String userId);
}
