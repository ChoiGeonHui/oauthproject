package com.oauthproject.choi.config;

import com.oauthproject.choi.domain.Role;
import com.oauthproject.choi.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long seq;
    private String name, email;

    private Role role;

    public SessionUser(User user) {
        this.seq = user.getSeq();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }


}
