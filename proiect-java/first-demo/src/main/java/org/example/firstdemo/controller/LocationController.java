package org.example.firstdemo.controller;

import org.example.firstdemo.controller.problemsolving.Graph;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.example.firstdemo.controller.problemsolving.ProblemData;

@RestController
public class LocationController {
    Graph graphData=null;
    @PostMapping("/api/location")
    public Graph receiveLocationData(@RequestBody ProblemData locationData) {
        //System.out.println("Received data: " + locationData);
        graphData=locationData.generateGraphFrom();
        return graphData;
    }
    @GetMapping("/api/location")
    public Graph getGraph(){
        return graphData;
    }

}
