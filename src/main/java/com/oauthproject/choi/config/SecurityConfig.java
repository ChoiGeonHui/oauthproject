package com.oauthproject.choi.config;


import com.oauthproject.choi.domain.Role;
import com.oauthproject.choi.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.jsp",
                        "/home/**",
                        "/test/**",
                        "/test1/**")
                .permitAll()
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name()) //사용자 등급별 웹페이지 접근 권한
                .antMatchers("/user/**").hasRole(Role.USER.name()) //사용자 등급별 웹페이지 접근 권한
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/test1")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied_page") /**권한이 없는 대상이 접속을 시도 했을 때*/
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

    }


}
