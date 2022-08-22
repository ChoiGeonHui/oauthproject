package com.oauthproject.choi.service;


import com.oauthproject.choi.config.OAuthAttributes;
import com.oauthproject.choi.config.SessionUser;
import com.oauthproject.choi.domain.User;
import com.oauthproject.choi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrantonId = userRequest
                .getClientRegistration()
                .getRegistrationId();

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes
                .of(registrantonId,userNameAttributeName,oAuth2User.getAttributes());

        User user = saveOrUdate(attributes);
        httpSession.setAttribute("user",new SessionUser(user));
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );

    }

    private User saveOrUdate(OAuthAttributes attributes){
        User user;
        if (userRepository.findByEmail(attributes.getEmail()) != null) {
            user = userRepository.findByEmail(attributes.getEmail());
        } else {
            user = attributes.toEntity();
            userRepository.save(user);
            user = userRepository.findByEmail(attributes.getEmail());
        }
        return user;
    }

}
