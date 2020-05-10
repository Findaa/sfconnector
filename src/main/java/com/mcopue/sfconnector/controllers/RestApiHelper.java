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
public class RestApiHelper {
    public RestApiHelper(SecurityVariables sv) {
        this.sv = sv;
    }
    SecurityVariables sv;
    HttpClient httpClient = HttpClients.createDefault();
    HttpResponse res;

    @Getter
    private final String opportunityQuery = "SELECT+Id,Name,Amount,CloseDate,StageName+from+Opportunity";

    public String getOpportunities(String query) {
        System.out.println("Get Opps");
        HttpGet get = new HttpGet("https://mcopue-dev-ed.lightning.force.com/" + "services/data/v43.0/query?q=" + query);
        get.setHeader("Authorization", "Bearer " + sv.getAccess_token());

        try {
            res = httpClient.execute(get);
            JSONArray filtered = new JSONObject(EntityUtils.toString(res.getEntity())).getJSONArray("records");
            return filtered.toString();
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
