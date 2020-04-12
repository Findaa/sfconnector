package com.mcopue.sfconnector;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    LoginControllerHelper helper;

    public LoginController(LoginControllerHelper helper) {
        this.helper = helper;
    }

    //todo: implement template
    @RequestMapping("/salesforce")
    public String displaySalesforceLogin(){
        return "";
    }

    @GetMapping("/login")
    public String runLogin(HttpSession session){
//        String username = session.getAttribute("username").toString();
//        String password = session.getAttribute("password").toString();
        helper.login("username", "password");
        return "redirect:/after_login";
    }
}