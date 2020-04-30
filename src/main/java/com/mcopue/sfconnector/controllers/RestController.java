package com.mcopue.sfconnector.controllers;

import com.mcopue.sfconnector.services.State;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RestController {
    public RestController(State state, RestControllerHelper helper) {
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
}
