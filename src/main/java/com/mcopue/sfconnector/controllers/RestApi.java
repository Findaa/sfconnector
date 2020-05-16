package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.services.State;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@SessionScope
public class RestApi {
    public RestApi(RestApiHelper helper) {
        this.helper = helper;
    }

    RestApiHelper helper;

    @GetMapping("/uid")
    @ResponseBody
    public String getUserId(){
        return helper.getAccountName("0013X00002aMc2cQAC");
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/opportunities")
    @ResponseBody
    public String getOpportunities(){
        System.out.println("Opportunities controller");
//        System.out.println("Helper call: " + String.valueOf(helper.getOpportunities(helper.getOpportunityQuery())));
        return helper.getOpportunities(helper.getOpportunityQuery());
    }

    @GetMapping("/issfauthorized")
    @ResponseBody
    public Boolean checkIfSalesforceIsAuthorised(){
        return helper.getAccountName("0013X00002aMc2cQAC").length() == 35;
    }

    @GetMapping("/forex")
    @ResponseBody
    public String getForexData(){
        System.out.println("returning forex data: \n" + helper.getForexData());

        return helper.getForexData().toString();
    }

    @RequestMapping("/searchforexdate")
    @ResponseBody
    public String getForexDay(@RequestBody String date){
        System.out.println("Rest date: " + date);
        helper.getForexDay(date);
        return "redirect:http://localhost:3000/analytics";
    }
}
