package com.oauthproject.choi.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChoiRepository {

    int selectCount();


}
