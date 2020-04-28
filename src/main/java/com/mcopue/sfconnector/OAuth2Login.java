package com.mcopue.sfconnector;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OAuth2Login {
    public OAuth2Login(SecurityVariables sv) {
        this.sv = sv;
    }

    SecurityVariables sv;
    //todo: implement fetchable credentials.
    private String endpointOauth = "/services/oauth2/token";
    private String endpointRest = "/services/data";

    String baseUri;
    Header oauthHeader;
    Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");
    Header allowAccessHeaders = new BasicHeader("Access-Control-Allow-Headers", "Content-Type");
    Header allowAccessMethods = new BasicHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    Header allowAccessOrigin = new BasicHeader("Access-Control-Allow-Origin", "*");

    public HttpResponse login() {
        org.apache.http.HttpResponse response = null;
        SfResponse sfResponse = null;
        OAuth2Login.UserCredentials userCredentials = new OAuth2Login.UserCredentials();
        String loginHostUri = "https://" + userCredentials.loginInstanceDomain + endpointOauth;

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(loginHostUri);
            StringBuffer requestBodyText = new StringBuffer("grant_type=password");
            requestBodyText.append("&client_id=");
            requestBodyText.append(sv.getConsumerKey());
            requestBodyText.append("&client_secret=");
            requestBodyText.append(sv.getConsumerSecret());
            requestBodyText.append("&username=");
            requestBodyText.append(sv.getUserName());
            requestBodyText.append("&password=");
            requestBodyText.append(sv.getPassword());

            StringEntity requestBody = new StringEntity(requestBodyText.toString());
            System.out.println("\n\n\nInfo");
            System.out.println("Host: " + loginHostUri);
            System.out.println("String entity: " + requestBodyText.toString());
//            requestBody.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(requestBody);
            httpPost.addHeader(prettyPrintHeader);
//            httpPost.addHeader(allowAccessHeaders);
//            httpPost.addHeader(allowAccessMethods);
//            httpPost.addHeader(allowAccessOrigin);
            System.out.println("Assert entities: " + httpPost.getEntity().equals(requestBody));
            response = httpClient.execute(httpPost);
//            System.out.println("user creds: " + sv.getUserName() + sv.getPassword() + sv.getConsumerSecret());
            System.out.println("response code: " + response.getStatusLine().getStatusCode() + "\n\n");

            if (response.getStatusLine().getStatusCode() == 200) {
                String response_string = EntityUtils.toString(response.getEntity());
                try {
                    JSONObject json = new JSONObject(response_string);
                    sfResponse = new SfResponse(json);
                    LoginControllerHelper.fire(json);
                    System.out.println("JSON returned by response: +\n" + json.toString(1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                baseUri = sfResponse.instance_url + endpointRest + "/v" + userCredentials.apiVersion + ".0";
                oauthHeader = new BasicHeader("Authorization", "OAuth " + sfResponse.access_token);
                System.out.println("XD");

            } else {
                System.out.println(response.getStatusLine().getStatusCode() + " in oauth2 login try with username: " + userCredentials.userName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static class SfResponse {
        public SfResponse() {
        }
        public SfResponse(JSONObject json) {
            try {
                id = json.getString("id");
                issued_at = json.getString("issued_at");
                instance_url = json.getString("instance_url");
                signature = json.getString("signature");
                access_token = json.getString("access_token");
                flag = true;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String id;
        String issued_at;
        String instance_url;
        String signature;
        String access_token;
        boolean flag = false;
    }

    class UserCredentials {
        String loginInstanceDomain = "mcopue-dev-ed.lightning.force.com";
        String apiVersion = "47";
        String userName = sv.getUserName();
        String password = sv.getPassword();
        String consumerKey = sv.consumerKey;
        String consumerSecret = sv.consumerSecret;
    }

}
