package com.oauthproject.choi.repository;

import com.oauthproject.choi.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface UserRepository {

    User findByEmail(String email);


    @Transactional
    void save(User user);



}
