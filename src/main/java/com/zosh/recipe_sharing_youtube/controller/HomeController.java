package com.zosh.recipe_sharing_youtube.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping()
    public ResponseEntity<String> homeController() {
        String responseBody = "Welcome to recipe sharing app";
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
