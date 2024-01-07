package com.looptrix.looptrix.controllers;

import com.looptrix.looptrix.Data.User;
import com.looptrix.looptrix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "user_signup";
    }

    @PostMapping("/user/create")
    public String storeUser(@RequestParam String userName, String password, String email, String firstName,
            String lastName) {
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        return userService.createUser(user).getUserName();
    }
}
