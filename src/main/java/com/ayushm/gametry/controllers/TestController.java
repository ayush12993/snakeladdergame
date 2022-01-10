package com.ayushm.gametry.controllers;

import com.ayushm.gametry.model.login.User;
import com.ayushm.gametry.repository.loginrepo.UserRepository;
import com.ayushm.gametry.service.GameService;
import com.ayushm.gametry.service.UserDeleting;
import com.ayushm.gametry.service.UserGenerating;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGenerating usersGenerating;

    @Autowired
    UserDeleting userDeleting;


    @Operation(
            summary = "Yo! Start creating Bot Check to see how the Api works! \nThis one only for testing Purposes!",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Unsuccessful"),
                    @ApiResponse(responseCode = "400", description = "UnSuccessful")
            }
    )
    @GetMapping("/allUsers")
    public List<String> allUsers() {
        List<User> userList = userRepository.findAll();
        List<String> userNameList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            String tempUserName =  userList.get(i).getUsername();
            userNameList.add(tempUserName);
        }
        return userNameList;
    }

    @Operation(
            summary = "Yo! Start creating Bot Check to see how the Api works! \nThis one only for testing Purposes!",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Unsuccessful"),
                    @ApiResponse(responseCode = "400", description = "UnSuccessful")
            }
    )
    @GetMapping("/totalUsers")
    public long totalUsers(){

        return userRepository.countAllUsers();
    }
    @Autowired
    GameService gameService;
    @GetMapping("/getAllDet")
    public String getAllDet(Authentication authentication,User user) throws JsonProcessingException {
        gameService.Authorization(authentication);
        return user.getUsername();
    }

    @Operation(
            summary = "Yo! Start creating Bot Check to see how the Api works! \nThis one only for testing Purposes!",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Unsuccessful"),
                    @ApiResponse(responseCode = "400", description = "UnSuccessful")
            }
    )
    @PostMapping("/generateUsers/{num}")
    public long registerUser(@PathVariable int num){

        usersGenerating.generateUsers(num);
        return userRepository.countAllUsers();
    }

    @Operation(
            summary = "Yo! Start creating Bot Check to see how the Api works! \nThis one only for testing Purposes!",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Unsuccessful"),
                    @ApiResponse(responseCode = "400", description = "UnSuccessful")
            }
    )
    @PostMapping("/deleteUsers")
    public void deleteUser(){
        userDeleting.userDeletion();
    }


    @Operation(
            summary = "Yo! Start creating Bot Check to see how the Api works! \nThis one only for testing Purposes!",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Unsuccessful"),
                    @ApiResponse(responseCode = "400", description = "UnSuccessful")
            }
    )
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess(Authentication authentication) {
        authentication.getDetails();
        return "User Content.";
    }

    @Operation(
            summary = "Yo! Start creating Bot Check to see how the Api works! \nThis one only for testing Purposes!",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Unsuccessful"),
                    @ApiResponse(responseCode = "400", description = "UnSuccessful")
            }
    )
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @Operation(
            summary = "Yo! Start creating Bot Check to see how the Api works! \nThis one only for testing Purposes!",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful"),
                    @ApiResponse(responseCode = "404", description = "Unsuccessful"),
                    @ApiResponse(responseCode = "400", description = "UnSuccessful")
            }
    )
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
