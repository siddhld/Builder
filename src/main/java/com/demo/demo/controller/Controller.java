package com.demo.demo.controller;

import com.demo.demo.model.Lead;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.status(HttpStatus.OK).body("Welcome home");
    }

    @PostMapping("/json")
    public ResponseEntity<Lead> saveJsonLead(@RequestBody Lead lead){
        if(lead==null || lead.getProjectName()==null || lead.getProjectName().equals("") || lead.getResponderName() == null || lead.getResponderName().equals("") || lead.getResponderEmail() == null || lead.getResponderEmail().equals("") || lead.getResponderPhone() == null || lead.getResponderPhone().equals("")){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(lead);
    }

    @PostMapping("/url")
    public ResponseEntity<String> saveUrlLead(
            @RequestParam(value = "responderName", required = true) String responderName,
            @RequestParam(value = "responderPhone", required = true) String responderPhone,
            @RequestParam(value = "responderEmail", required = true) String responderEmail,
            @RequestParam(value = "projectName", required = true) String projectName) {

        return ResponseEntity.status(HttpStatus.OK).body("Lead Captured Successfully");
    }

}
