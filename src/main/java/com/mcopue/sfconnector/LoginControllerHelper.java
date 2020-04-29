package com.mcopue.sfconnector;

import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class LoginControllerHelper {
    public LoginControllerHelper(SecurityVariables sv, OAuth2Login log) {
        this.sv = sv;
        this.log = log;
    }
    SecurityVariables sv;
    OAuth2Login log;
    @Setter
    String id;
    @Setter
    String issued_at;
    @Setter
    String instance_url;
    @Setter
    String signature;
    @Setter
    String access_token;
    static String[] res = new String[5];

    public void login(String username, String password) {
        log.login();
    }

    public void postLogin(String code){
        sv.setAuthorizedCode(code);
        System.out.println(code);
    }
}



