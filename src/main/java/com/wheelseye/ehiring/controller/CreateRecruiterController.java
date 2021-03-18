package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.dto.RecruiterDTO;
import com.wheelseye.ehiring.entity.Recruiter;
import com.wheelseye.ehiring.request.CreateRecruiterReq;
import com.wheelseye.ehiring.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CreateRecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/recruiterinfo")
    public RecruiterDTO create(@RequestBody CreateRecruiterReq req) throws Exception{
        return recruiterService.create(req);
    }

}
