package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.dto.SeekerDTO;
import com.wheelseye.ehiring.entity.Seeker;
import com.wheelseye.ehiring.entity.User;
import com.wheelseye.ehiring.entity.UserAccount;
import com.wheelseye.ehiring.request.ChangePasswordReq;
import com.wheelseye.ehiring.request.CreateUserReq;
import com.wheelseye.ehiring.request.UserSigninReq;
import com.wheelseye.ehiring.service.UserAuthenticationService;
import com.wheelseye.ehiring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserDetailsController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationService userAuthenticationService;


    // get all user api
    @GetMapping("/user")
    public PageDTO<User> getAllUser(@RequestParam(defaultValue = "0")Integer pageNo,
                                     @RequestParam(defaultValue = "2") Integer pageSize){
        return userService.getAllUser(pageNo,pageSize);
    }

    // user sign up api

    @PostMapping("/userSignup")
    public User create(@RequestBody CreateUserReq req) throws  Exception{
        return userService.create(req);
    }


    // user sign in api
    @GetMapping("/userSignin")
    public User getUserDetails(@RequestBody UserSigninReq userSigninReq) throws Exception{
        if(userAuthenticationService.getUserByDetails(userSigninReq)!=null)
            return userAuthenticationService.getUserByDetails(userSigninReq);
        else
            throw new Exception("User Authentication Failed");
    }

    // get user details by id

    @GetMapping("/userdetails/{userid}")
    public User userdetails(@PathVariable(value = "userid") Integer userid) {
        return userService.getUserById(userid);
    }


    //Update password of user
    @PutMapping("/user/{userid}/password")
    public UserAccount updateUserPassword(@PathVariable(value = "userid" ) Integer userid, @RequestBody ChangePasswordReq changePasswordReq) throws Exception{
        return userService.updatePassword(userid, changePasswordReq);
    }

}
