package com.spa.Online.Spa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    public ResponseEntity<String> HomeController(){
        return new ResponseEntity<>("Welcome to spa project", HttpStatus.OK);

    }
}
