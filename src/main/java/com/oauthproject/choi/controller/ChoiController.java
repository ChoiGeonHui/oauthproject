package com.oauthproject.choi.controller;

import com.oauthproject.choi.service.ChoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Controller
public class ChoiController {

    @Autowired
    private ChoiService choiService;

    @ResponseBody
    @RequestMapping("/test")
    public String test(){

        int i = 0;
        i = choiService.selectCount();

        return "Hellow Worold! : " + i;
    }


    @RequestMapping("/test1")
    public String testjsp(){
        return "test";
    }


    @ResponseBody
    @RequestMapping("/admin")
    public String adminPage(){
        return "adminPage : success";
    }

    @RequestMapping("/")
    public String list(){
        return "list";
    }


}
