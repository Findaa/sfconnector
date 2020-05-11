package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.SecurityVariables;
import com.mcopue.sfconnector.services.State;
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
import java.util.List;

@Component
public class RestApiHelper {
    public RestApiHelper(SecurityVariables sv, State state) {
        this.sv = sv;
        this.state = state;
    }

    SecurityVariables sv;
    HttpClient httpClient = HttpClients.createDefault();
    HttpResponse res;
    State state;

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

    public List<String> getForexData() {
        System.out.println("In getForexData");
        return state.getStateList();
    }

    public List<String> getForexDay(String date) {
        String month = date.substring(9, 12);
        String day = date.substring(13, 16);
        String year = date.substring(16, 20);
        System.out.println(month);
        System.out.println(day);
        System.out.println(year);

        if (month.equals("Jan")) month = "01";
        else if (month.equals("Feb")) month = "02";
        else if (month.equals("Mar")) month = "03";
        else if (month.equals("Apr")) month = "04";
        else if (month.equals("May")) month = "05";
        else if (month.equals("Jun")) month = "06";
        else if (month.equals("Jul")) month = "07";
        else if (month.equals("Aug")) month = "08";
        else if (month.equals("Sep")) month = "09";
        else if (month.equals("Oct")) month = "10";
        else if (month.equals("Nov")) month = "11";
        else if (month.equals("Dec")) month = "12";

        String responseBody = "";
        String url = "http://api.currencylayer.com/historical?access_key="
                + sv.getForexToken()
                + "&date=" + year + "-" + month + "-" + day.trim() + "&currencies=USD,AUD,CAD,PLN,MXN&format=1";
        System.out.println("Url: " + url);
        HttpGet get = new HttpGet(url);
        try {
            res = httpClient.execute(get);
            JSONObject json = new JSONObject(EntityUtils.toString(res.getEntity()));
            responseBody = json.toString();
            System.out.println("json: " + json.toString());
            System.out.println("len: " + json.length());
            System.out.println("resp body: " + responseBody);
            state.updateStateList(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return state.getStateList();
    }
}
