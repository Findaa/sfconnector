package com.mcopue.sfconnector.controllers;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestControllerHelper {
    public List<String> createObjectList(String x){
        List<String> list = new ArrayList<>();
        list.add(x);
        return list;
    }
}
