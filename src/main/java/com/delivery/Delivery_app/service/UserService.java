package com.delivery.Delivery_app.service;

import com.delivery.Delivery_app.controller.AutoGenerateController;
import com.delivery.Delivery_app.entity.AutoGenerate;
import com.delivery.Delivery_app.entity.Login;
import com.delivery.Delivery_app.entity.User;
import com.delivery.Delivery_app.repository.AutoGenerateRepository;
import com.delivery.Delivery_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AutoGenerateController autoGenerateController;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user) {
        user.setCartId(autoGenerateController.getValue());
        user.setRole("NORMAL");
//        user.setPassword(hashing(user.getPassword()));    //hashing the plain password text
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 = userRepository.save(user);
        return user1;
    }

    public boolean isLogin(Login login) {
        boolean isLog = userRepository.findById(login.getUserId()).get().isLogin();
        if(isLog) return true;
//        String enCodedPass = passwordEncoder.encode(login.getPassword());
//        String password = hashing(login.getPassword());
//        if(userRepository.checkLogin(login.getUserId(),enCodedPass) != null){
                User user = userRepository.findById(login.getUserId()).get();
                user.setLogin(true);
                userRepository.save(user);
                return true;
//            }
//        return false;
    }

    public boolean isLogout(Login login) {
        boolean isLog = userRepository.findById(login.getUserId()).get().isLogin();
        if(isLog) {
            User user = userRepository.findById(login.getUserId()).get();
            user.setLogin(false);
            userRepository.save(user);
            return false;
        }else {
            return true;
        }
    }





   /* public String hashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] bytePasswordArray = messageDigest.digest();
            StringBuilder hashPassword = new StringBuilder();
            for (byte b : bytePasswordArray) {
                hashPassword.append(String.format("%02x", b));
            }
            return hashPassword.toString();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }*/
}
