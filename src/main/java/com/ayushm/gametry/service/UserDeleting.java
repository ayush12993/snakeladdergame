package com.ayushm.gametry.service;

import com.ayushm.gametry.repository.loginrepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeleting {
    @Autowired
    private UserRepository userRepository;

    public void userDeletion(){
            userRepository.deleteAll();
    }
}
