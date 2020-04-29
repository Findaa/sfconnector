package com.mcopue.sfconnector;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    LoginControllerHelper helper;
    SecurityVariables sv;

    public LoginController(LoginControllerHelper helper, SecurityVariables sv) {
        this.helper = helper;
        this.sv=sv;
    }

    @GetMapping("/login")
    public String runLogin(HttpSession session) {
        session.setAttribute("clientId", sv.getConsumerKey());
        session.setAttribute("redirect", sv.getRedirect());
        return "login";
    }

    @GetMapping("/processLogin")
    public String processLog(HttpSession session) {
        helper.login("u", "p");
        return "";
    }

    @GetMapping("/auth}")
    public void saveCode(@RequestParam String code) {
        helper.postLogin(code);
    }

    @GetMapping("/")
    public String displayIndex(){
        return "index";
    }
}
