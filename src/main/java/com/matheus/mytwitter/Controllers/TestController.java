package com.matheus.mytwitter.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }
}
