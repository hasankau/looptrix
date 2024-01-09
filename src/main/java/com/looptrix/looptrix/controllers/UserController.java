package com.looptrix.looptrix.controllers;

import com.looptrix.looptrix.Data.User;
import com.looptrix.looptrix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    BCryptPasswordEncoder hashPassword = new BCryptPasswordEncoder();

    @PostMapping("/user/store")
    public String storeUser(@ModelAttribute User user) {

        user.setPassword(hashPassword.encode(user.getPassword()));
        System.out.println(user.getFirstName());
        return userService.createUser(user).getUserName();
    }
}
