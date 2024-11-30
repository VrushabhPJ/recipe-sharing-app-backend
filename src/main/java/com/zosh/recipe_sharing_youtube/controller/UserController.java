package com.zosh.recipe_sharing_youtube.controller;

import com.zosh.recipe_sharing_youtube.model.User;
import com.zosh.recipe_sharing_youtube.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    UserController(UserService userService) {
        this.userService= userService;
    }

    @GetMapping("/api/users/profile")
    public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {

        User user= userService.findUserByJwt(jwt);

        return user;
    }

}
