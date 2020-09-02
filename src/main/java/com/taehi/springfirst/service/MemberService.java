package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.member.MemberVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    public int joinUser(MemberVO memberVO);
}
