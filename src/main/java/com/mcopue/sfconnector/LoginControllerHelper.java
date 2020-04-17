package com.mcopue.sfconnector;

import lombok.Setter;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class LoginControllerHelper {
    public LoginControllerHelper(SecurityVariables vars, OAuth2Login log) {
        this.vars = vars;
        this.log = log;
    }

    SecurityVariables vars;
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
        //Commented as it is stored by now.
//        vars.setUserName(username);
//        vars.setPassword(password);
        log.login();
    }

    public static String[] fire(JSONObject json) {
        System.out.println("Try");
        try {
            System.out.println("Json in helper: " + json.toString());
            res[0] = json.getString("id");
            res[1] = json.getString("issued_at");
            res[2] = json.getString("signature");
            res[3] = json.getString("instance_url");
            res[4] = json.getString("access_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void postLogin(){
        BasicHeader oauthHeader = new BasicHeader("Authorization", "OAuth " + access_token);
        vars.setAuthHeader(oauthHeader);
        setId(res[0]);
        setIssued_at(res[1]);
        setSignature(res[2]);
        setInstance_url(res[3]);
        setAccess_token(res[4]);
        vars.acceptResponse(id, issued_at, instance_url, signature, access_token);
        System.out.println("GREAT: \nId: " + id + " issue: " + issued_at + " token: " + access_token);
        System.out.println("generated header: " + vars.getAuthHeader());
    }
}



