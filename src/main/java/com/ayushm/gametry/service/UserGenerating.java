package com.ayushm.gametry.service;

import com.ayushm.gametry.model.login.User;
import com.ayushm.gametry.repository.loginrepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserGenerating {
    @Autowired
    UserRepository userRepository;

    private User generateRandomUser(){

        User user = new User();
        String tempName = UUID.randomUUID().toString();
        user.setUsername(tempName);
        user.setEmail(tempName + "@" + "test.com");
        user.setPassword("Snake");
        return user;
    };


    public void generateUsers(int numofusers){
        for (int i=0;i<numofusers;i++){
            userRepository.save(generateRandomUser());
        }
    }
}