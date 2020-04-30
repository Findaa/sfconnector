package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.SecurityVariables;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    public List<String> createObjectList(String x) {
        List<String> list = new ArrayList<>();
        list.add(x);
        return list;
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
