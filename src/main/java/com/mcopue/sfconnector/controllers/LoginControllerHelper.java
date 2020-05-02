package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.SecurityVariables;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
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

        HttpClient httpClient = HttpClients.createDefault();
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
            res = httpClient.execute(post);
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            String responseEntity = EntityUtils.toString(res.getEntity());

            JSONObject json = new JSONObject(responseEntity);

            System.out.println(json + " : json");
            sv.setAccess_token(json.getString("access_token"));
            sv.setSignature(json.getString("signature"));
            sv.setId(json.getString("id"));
            sv.setIssued_at(json.getString("issued_at"));
            sv.setSignature(json.getString("signature"));
            sv.setInstance_url(json.getString("instance_url"));
        } catch(IOException io){
            io.printStackTrace();
        }
    }
}



