package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.dto.RecruiterDTO;
import com.wheelseye.ehiring.dto.SeekerDTO;
import com.wheelseye.ehiring.entity.Recruiter;
import com.wheelseye.ehiring.entity.Seeker;
import com.wheelseye.ehiring.request.CreateSeekerReq;
import com.wheelseye.ehiring.service.RecruiterService;
import com.wheelseye.ehiring.service.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController


public class EntityDetailsController {
    @Autowired
    private SeekerService seekerService;

    @Autowired
    private RecruiterService recruiterService;

    @GetMapping("/seekers")
    private PageDTO<SeekerDTO > getAllSeeker(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize){
        return seekerService.getAllSeeker(pageNo,pageSize);
    }

    @GetMapping("/recruiters")
    private PageDTO<RecruiterDTO> getAllRecruiter(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "2") Integer pageSize){
        return recruiterService.getAllRecruiter(pageNo,pageSize);
    }


}
