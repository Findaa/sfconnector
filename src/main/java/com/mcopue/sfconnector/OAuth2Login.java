package com.mcopue.sfconnector;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

@Component
public class OAuth2Login {
    public OAuth2Login(SecurityVariables sv) {
        this.sv = sv;
    }

    SecurityVariables sv;
    //todo: implement fetchable credentials.

    public String login(){
        String host = "https://login.salesforce.com/services/oauth2/authorize?";
        String clientId = "client_id=" + sv.getConsumerKey();
        String redirectUrl = "&redirect_url=" + sv.getRedirect();
        String responseType = "&response_type=code";
        System.out.println("In login method");
        return "redirect:/" + host+clientId+redirectUrl+responseType;
    }
}
