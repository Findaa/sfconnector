package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.SecurityVariables;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    LoginControllerHelper helper;
    SecurityVariables sv;

    public LoginController(LoginControllerHelper helper, SecurityVariables sv) {
        this.helper = helper;
        this.sv = sv;
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
}
