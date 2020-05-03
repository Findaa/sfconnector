package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.domain.OpportunitySf;
import com.mcopue.sfconnector.services.State;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RestApi {
    public RestApi(State state, RestControllerHelper helper) {
        this.state = state;
        this.helper = helper;
    }

    State state;
    RestControllerHelper helper;

    @GetMapping("/uid")
    @ResponseBody
    public List<String> getUserId(){
        return helper.createObjectList(helper.getAccountName("0013X00002aMc2cQAC"));
    }

    @GetMapping("/opportunities")
    @ResponseBody
    public List <OpportunitySf> getOpportunities(){
        System.out.println("Opportunities controller");
        return helper.getOpportunities(helper.getOpportunityQuery());
    }

    @GetMapping("/issfauthorized")
    @ResponseBody
    public Boolean checkIfSalesforceIsAuthorised(){
        System.out.println("Checked auth, returned: " + (helper.getAccountName("0013X00002aMc2cQAC").length() == 35) + System.currentTimeMillis());
        System.out.println(helper.getAccountName("0013X00002aMc2cQAC"));
        return helper.getAccountName("0013X00002aMc2cQAC").length() == 35;
    }
}
