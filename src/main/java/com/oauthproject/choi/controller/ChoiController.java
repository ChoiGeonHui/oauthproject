package com.oauthproject.choi.controller;

import com.oauthproject.choi.service.ChoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ChoiController {

    @Autowired
    private ChoiService choiService;

    @ResponseBody
    @RequestMapping("/test")
    public String test() {

        int i = 0;
        i = choiService.selectCount();

        return "Hellow Worold! : " + i;
    }


    @RequestMapping("/test1")
    public String testjsp() {
        return "test";
    }


    @ResponseBody
    @RequestMapping("/admin")
    public String adminPage() {
        return "adminPage : success";
    }

    @ResponseBody
    @RequestMapping("/user")
    public String userPage() {
        return "userPage : success";
    }

    @RequestMapping("/list")
    public String list() {
        return "list";
    }

    @RequestMapping("accessDenied_page")
    public String accessDeniedPage() {
        return "access";
    }

}
