package com.oauthproject.choi.service;

import com.oauthproject.choi.repository.ChoiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChoiService {

    private final ChoiRepository choiRepository;

    public int selectCount(){
        return choiRepository.selectCount();
    }


}
