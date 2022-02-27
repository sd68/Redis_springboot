package com.dong.controller;

import com.dong.pojo.User;
import com.dong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    //    @GetMapping("/getUserById/{id}")
//    Object getUserById(@PathVariable("id") int id){
//        return userService.getUserById(id);
//    }
    @GetMapping("/getUserById/{id}")
    Object getUserById(@PathVariable("id") int id) {
        ExecutorService es = Executors.newFixedThreadPool(200);
        for(int i=0;i<500;i++){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    userService.getUserById(id);
                }
            });
        }
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUser")
    List<User> getAllUser() {
        return userService.getAllUser();
    }
}
