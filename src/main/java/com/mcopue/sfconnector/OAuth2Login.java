package com.mcopue.sfconnector;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class OAuth2Login {
    public OAuth2Login(SecurityVariables sc) {
        this.sc = sc;
    }
    SecurityVariables sc;

    //todo: implement fetchable credentials.
    private String endpointOauth = "/services/oauth2/token";
    private String endpointRest = "/services/data";

    String baseUri;
    Header oauthHeader;
    Header prettyPrintHeader = new BasicHeader("X-PrettyPrint", "1");

    public HttpResponse login() {
        org.apache.http.HttpResponse response = null;
        SfResponse sfResponse = null;
        OAuth2Login.UserCredentials userCredentials = new OAuth2Login.UserCredentials();
        String loginHostUri = "https://" + userCredentials.loginInstanceDomain + endpointOauth;

        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(loginHostUri);

            StringBuffer requestBodyText = new StringBuffer("grant_type=password");
            requestBodyText.append("&username=");
            requestBodyText.append(userCredentials.userName);
            requestBodyText.append("&password=");
            requestBodyText.append(userCredentials.password);
            requestBodyText.append("&client_id=");
            requestBodyText.append(userCredentials.consumerKey);
            requestBodyText.append("&client_secret=");
            requestBodyText.append(userCredentials.consumerSecret);

            StringEntity requestBody = new StringEntity(requestBodyText.toString());
            requestBody.setContentType("application/x-www-form-urlencoded");

            httpPost.setEntity(requestBody);
            httpPost.addHeader(prettyPrintHeader);

            response = httpClient.execute(httpPost);
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
        String id;
        String issued_at;
        String instance_url;
        String signature;
        String access_token;
        boolean flag = false;

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
    }

    private String getBody(InputStream inputStream) {
        String result = "";
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(inputStream)
            );
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
                result += "\n";
            }
            in.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    class UserCredentials {
        String loginInstanceDomain = "mcopue-dev-ed.lightning.force.com";
        String apiVersion = "47";
        String userName = sc.getUserName();
        String password = sc.getPassword();
        String consumerKey = sc.consumerKey;
        String consumerSecret = sc.consumerSecret;
        String grantType = "password";
    }

}
