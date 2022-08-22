package com.oauthproject.choi.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("user")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class User {

    private Long seq;
    private String name;
    private String email;
    private Role role;


    @Builder
    public User(String name,String email,Role role){
        this.name = name;
        this.email = email;
        this.role = role;

    }

    public String getRoleKey(){
        return this.role.getKey();
    }


}
