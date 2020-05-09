package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.services.State;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@SessionScope
public class RestApi {
    public RestApi(State state, RestApiHelper helper) {
        this.state = state;
        this.helper = helper;
    }

    State state;
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
}
