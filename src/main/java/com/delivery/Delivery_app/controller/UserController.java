package com.delivery.Delivery_app.controller;

import com.delivery.Delivery_app.entity.Login;
import com.delivery.Delivery_app.entity.User;
import com.delivery.Delivery_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    register user for the first time
    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

//    login user
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
        boolean flag = userService.isLogin(login);
        if(flag) {
            String msg = "You have Successfully Login";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else {
            String msg = "Please enter valid userId or password";
            return new ResponseEntity<>(msg,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody Login login){
        if(!userService.isLogout(login)){
            String msg = "You have Successfully Logout";
            return new ResponseEntity<>(msg,HttpStatus.OK);
        }else{
            String msg = "Logout Failed";
            return new ResponseEntity<>(msg,HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
