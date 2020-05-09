package com.mcopue.sfconnector.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mcopue.sfconnector.SecurityVariables;
import lombok.Getter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestControllerHelper {
    public RestControllerHelper(SecurityVariables sv) {
        this.sv = sv;
    }
    SecurityVariables sv;
    HttpClient httpClient = HttpClients.createDefault();
    HttpResponse res;

    @Getter
    private final String opportunityQuery = "SELECT+Id,Name+from+Opportunity";

    public String getOpportunities(String query) {
        System.out.println("Get Opps");
        HttpGet get = new HttpGet("https://mcopue-dev-ed.lightning.force.com/" + "services/data/v43.0/query?q=" + query);
        get.setHeader("Authorization", "Bearer " + sv.getAccess_token());

        try {
            res = httpClient.execute(get);
            JSONArray filtered = new JSONObject(EntityUtils.toString(res.getEntity())).getJSONArray("records");
            System.out.println("Filtered: " + filtered);
            StringBuilder returnStr = new StringBuilder("[");
            for (int n = 0; n < filtered.length(); n++) {
                String a = String.valueOf(filtered.get(n));
                System.out.println("Json string: " + a.substring(106));
                returnStr.append("{").append(a.substring(106)).append(",");
            }
            returnStr.append("]");
            returnStr.deleteCharAt(returnStr.length() - 2);
            System.out.println("Ret str: " + returnStr);
            return returnStr.toString();
        } catch (IOException io) {
            io.printStackTrace();
            System.out.println("JSON Opps: Error");
            return "{\"Id\":\"Error,\"Name\":\"Error\"}";
        }
    }

    public String getAccountName(String id) {
        String responseBody = "Loading...";
        HttpGet get = new HttpGet("https://mcopue-dev-ed.lightning.force.com"
                + "/services/data/v43.0/sobjects/Account/"
                + id
                + "?fields=Name");
        get.setHeader("Authorization", "Bearer " + sv.getAccess_token());
        try {
            res = httpClient.execute(get);
            JSONObject json = new JSONObject(EntityUtils.toString(res.getEntity()));
            responseBody = json.getString("Name");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Get acc name: " + responseBody);
        return responseBody;
    }
}
