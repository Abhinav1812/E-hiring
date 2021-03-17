package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.dto.JobAppliedDTO;
import com.wheelseye.ehiring.dto.JobOpeningsDTO;
import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.dto.SeekerDTO;
import com.wheelseye.ehiring.entity.JobApplied;
import com.wheelseye.ehiring.entity.JobOpenings;
import com.wheelseye.ehiring.entity.Seeker;
import com.wheelseye.ehiring.request.ApplyJobByUserReq;
import com.wheelseye.ehiring.request.ChangePasswordReq;
import com.wheelseye.ehiring.request.ChangeSeekerDetailsReq;
import com.wheelseye.ehiring.service.JobAppliedService;
import com.wheelseye.ehiring.service.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class SeekerHomePageController {

    @Autowired
    private JobAppliedService jobAppliedService;

    @Autowired
    private SeekerService seekerService;


    @GetMapping("/jobsapplied/{userid}")
    private PageDTO<JobAppliedDTO> allJobsApplied(@RequestParam(defaultValue = "0") Integer pageNo , @RequestParam(defaultValue = "3")
            Integer pageSize,@PathVariable(value = "userid") Integer userid){
        return jobAppliedService.getJobsAppliedByUser(userid,pageNo,pageSize);
    }

    @PostMapping("/applyforjob")
    private String applyForJob(@RequestBody ApplyJobByUserReq applyJobByUserReq){
        if(jobAppliedService.applyforJob(applyJobByUserReq).equals("Successfully"))
            return "Application Successfully Submitted";
        else
            return "Application cant be submitted";
    }

    @GetMapping("/jobsavailable/{userid}")
    private List<JobOpeningsDTO> allJobsAvailable(@PathVariable(value = "userid") Integer userid){
        return seekerService.getJobsAvailableForUser(userid);
    }

    @GetMapping("/seekerdetails/{userid}")
    private SeekerDTO seekerDetails(@PathVariable(value = "userid") Integer userid){
        return seekerService.getSeekerDetails(userid);
    }

    //update seeker details
    @PutMapping("/updateSeekerDetails/{userid}")
    private SeekerDTO updateSDetails(@PathVariable(value = "userid") Integer userid, @RequestBody ChangeSeekerDetailsReq changeSeekerDetailsReq) throws Exception{
        return seekerService.updateSeekerDetails(userid,changeSeekerDetailsReq);
    }

}
