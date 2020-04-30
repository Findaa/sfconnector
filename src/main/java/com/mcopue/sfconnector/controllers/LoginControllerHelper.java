package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.SecurityVariables;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginControllerHelper {
    public LoginControllerHelper(SecurityVariables sv) {
        this.sv = sv;
    }
    SecurityVariables sv;

    public void postLogin(String code){
        sv.setAuthorizedCode(code);
        System.out.println(code);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse res = null;
        HttpPost post = new HttpPost("https://login.salesforce.com/services/oauth2/token");
        String requestBody = "grant_type=authorization_code"
                + "&code=" + sv.getAuthorizedCode()
                + "&client_id=" + sv.getConsumerKey()
                + "&client_secret=" + sv.getConsumerSecret()
                + "&redirect_uri=" + sv.getRedirect();
        try {
            StringEntity requestEntity = new StringEntity(requestBody);
            requestEntity.setContentType("application/x-www-form-urlencoded");
            post.setEntity(requestEntity);
            System.out.println("Entity: \n" + EntityUtils.toString(requestEntity));
            System.out.println("Post Entity: \n" + EntityUtils.toString(post.getEntity()));
            res = httpClient.execute(post);
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println("Response Entity: \n" + EntityUtils.toString(res.getEntity()));
        } catch(IOException io){
            io.printStackTrace();
        }
    }
}



