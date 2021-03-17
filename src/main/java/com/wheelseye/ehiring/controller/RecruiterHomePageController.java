package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.dto.JobOpeningsDTO;
import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.dto.RecruiterDTO;
import com.wheelseye.ehiring.entity.JobOpenings;
import com.wheelseye.ehiring.entity.Recruiter;
import com.wheelseye.ehiring.entity.Seeker;
import com.wheelseye.ehiring.request.AddJobReq;
import com.wheelseye.ehiring.request.ChangeRecruiterDetailsReq;
import com.wheelseye.ehiring.request.ChangeSeekerDetailsReq;
import com.wheelseye.ehiring.service.JobOpeningsService;
import com.wheelseye.ehiring.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecruiterHomePageController {

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private JobOpeningsService jobOpeningsService;

    @GetMapping("/jobsposted/{recid}")
    private PageDTO<JobOpeningsDTO> AllJobsPosted(@PathVariable(value = "recid") Integer recid , @RequestParam(defaultValue = "0") Integer pageNo,
    @RequestParam(defaultValue = "2") Integer pageSize){
        return jobOpeningsService.getAllJobsPostedByRec(recid,pageNo,pageSize);
    }

    @PostMapping("/addjob")
    private String create(@RequestBody AddJobReq req){
        if(jobOpeningsService.create(req)!=null)
            return "Job Created";
        else
            return "Job cant be Created";
    }
    // get recruiter details

    @GetMapping("/recdetails/{userid}")
    private RecruiterDTO recruiterDetails(@PathVariable(value = "userid") Integer userid){
        return recruiterService.getRecruiterDetails(userid);
    }

    @PutMapping("/updateRecDetails/{userid}")
    private RecruiterDTO updateRDetails(@PathVariable(value = "userid") Integer userid, @RequestBody ChangeRecruiterDetailsReq changeRecruiterDetailsReq) throws Exception{
        return recruiterService.updateRecruiterDetails(userid,changeRecruiterDetailsReq);
    }

    /*
    *  @PostMapping("/userSignup")
    private User create(@RequestBody CreateUserReq req){
        return userService.create(req);
    }
    * */

}
