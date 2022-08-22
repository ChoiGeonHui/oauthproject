package com.oauthproject.choi.config;

import com.oauthproject.choi.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String name, email;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }


}
