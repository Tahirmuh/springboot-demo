package de.learning.demo.controllers;

import de.learning.demo.model.model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @RequestMapping(value ="/hello", method = RequestMethod.GET, produces="application/json")
    public String hello() {
       return "Hello Spring Boot";


    }
}
