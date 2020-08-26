package com.taehi.springfirst.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private String userId;
    private String password;
    private String email;
    private boolean adult;

}
