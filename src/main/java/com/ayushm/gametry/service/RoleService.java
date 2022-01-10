package com.ayushm.gametry.service;

import com.ayushm.gametry.model.login.Role;
import com.ayushm.gametry.model.login.enums.ERole;
import com.ayushm.gametry.repository.loginrepo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    Role role= new Role();
    public void saverole(){
       user();
       moderator();
  admin();
    }

    public void user(){
        role.setId(1);
        role.setName(ERole.ROLE_USER);
        roleRepository.save(role);
    }

    public void moderator(){
        role.setId(2);
        role.setName(ERole.ROLE_MODERATOR);
        roleRepository.save(role);
    }
    public void admin(){
    role.setId(3);
        role.setName(ERole.ROLE_ADMIN);
        roleRepository.save(role);
}}
