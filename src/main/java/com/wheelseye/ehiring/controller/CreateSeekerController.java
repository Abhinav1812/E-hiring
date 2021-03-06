package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.dto.SeekerDTO;
import com.wheelseye.ehiring.entity.Seeker;
import com.wheelseye.ehiring.request.CreateSeekerReq;
import com.wheelseye.ehiring.service.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSeekerController {

    @Autowired
    private SeekerService seekerService;


    // take seker details and add to database

    @PostMapping("/seekerinfo")
    public SeekerDTO create(@RequestBody CreateSeekerReq req) throws Exception{
        return seekerService.create(req);
    }

    /*
    private User create(@RequestBody CreateUserReq req){
        return userService.create(req);
    }*/
}
