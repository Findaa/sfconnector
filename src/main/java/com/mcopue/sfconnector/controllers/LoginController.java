package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.SecurityVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class LoginController {

    LoginControllerHelper helper;

    @Resource(name = "sessionScopedBean")
    SecurityVariables sv;


    public LoginController(LoginControllerHelper helper) {
        this.helper = helper;
    }

    @GetMapping("/login")
    public String runLogin() {
        return "redirect:https://login.salesforce.com/services/oauth2/authorize?client_id=" + sv.getConsumerKey()
                + "&redirect_uri=" + sv.getRedirect()
                + "&response_type=code";
    }

    @GetMapping("/auth")
    public String saveCode(@RequestParam String code) {
        helper.postLogin(code);
        return "redirect:http://localhost:3000";
    }

    @GetMapping("/logout")
    public String logout(){
        sv.setAccess_token("");
        return "redirect:http://localhost:3000";
    }
}
