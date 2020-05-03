package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.SecurityVariables;
import com.mcopue.sfconnector.domain.OpportunitySf;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RestControllerHelper {
    public RestControllerHelper(SecurityVariables sv) {
        this.sv = sv;
    }
    SecurityVariables sv;
    HttpClient httpClient = HttpClients.createDefault();
    HttpResponse res;

    @Getter
    private final String opportunityQuery =
            "SELECT+Id,AccountId,Name,Amount,CloseDate,Stage+from+Opportunity";

    public List<String> createObjectList(String x) {
        List<String> list = new ArrayList<>();
        list.add(x);
        System.out.println(sv.getAccess_token());
        return list;
    }

    public List<OpportunitySf> getOpportunities(String query){
        System.out.println("Get Opps method");
        String responseBody = "Loading...";
        HttpGet get = new HttpGet("https://mcopue-dev-ed.lightning.force.com/"
        + "services/data/v43.0/query/?q=" + query);
        get.setHeader("Authorization", "Bearer " + sv.getAccess_token());
        try{
            res = httpClient.execute(get);
            System.out.println("In try res: " + res.toString());
            JSONObject json = new JSONObject(EntityUtils.toString(res.getEntity()));
            System.out.println(json);
            System.out.println("JSON Opps: " + json);

        } catch (IOException io){
            io.printStackTrace();
            System.out.println("JSON Opps: Error");
        } finally {
            System.out.println("JSON Opps: Finally" );
        }

        return null;
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
        return responseBody;
    }
}
