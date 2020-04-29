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
        URL url;
        System.out.println("In login method");
        try {
            System.out.println("In try method");
            url = new URL(host+clientId+redirectUrl+responseType);
            URLConnection con = url.openConnection();
        } catch (Exception e) {
            System.out.println("Error catched: ");
            e.printStackTrace();
        }
        return "redirect:/" + host+clientId+redirectUrl+responseType;
    }
}
