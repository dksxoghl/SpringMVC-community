package com.taehi.springfirst.service;

import com.taehi.springfirst.domain.member.MemberVO;
import com.taehi.springfirst.persistence.MemberDAO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO;

    @Override
    public int joinUser(MemberVO memberVO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
        return memberDAO.joinUser(memberVO);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberVO> optionalMemberVO = memberDAO.findByUserId(username);
        System.out.println(username + "유저네임" + optionalMemberVO);
//        MemberVO memberVO = optionalMemberVO.orElseThrow(()->new NoSuchElementException());
        MemberVO memberVO = optionalMemberVO.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (username.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new User(memberVO.getUserId(), memberVO.getPassword(), authorities);
    }
}
