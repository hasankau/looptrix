package com.looptrix.looptrix.controllers;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.looptrix.looptrix.services.UserService;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    private final ObjectMapper objectMapper;

    public IndexController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/users")
    public String users() {
        try {
            return objectMapper.writeValueAsString(userService.getAllUsers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
