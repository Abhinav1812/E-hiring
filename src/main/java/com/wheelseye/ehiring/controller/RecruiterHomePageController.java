package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.dto.*;
import com.wheelseye.ehiring.entity.JobApplied;
import com.wheelseye.ehiring.entity.JobOpenings;
import com.wheelseye.ehiring.entity.Recruiter;
import com.wheelseye.ehiring.entity.Seeker;
import com.wheelseye.ehiring.request.AddJobReq;
import com.wheelseye.ehiring.request.ChangeRecruiterDetailsReq;
import com.wheelseye.ehiring.request.ChangeSeekerDetailsReq;
import com.wheelseye.ehiring.service.JobAppliedService;
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

    @Autowired
    private JobAppliedService jobAppliedService;

    // get all job posted by a recruiter

    @GetMapping("/jobsposted/{recid}")
    public PageDTO<JobOpeningsDTO> AllJobsPosted(@PathVariable(value = "recid") Integer recid , @RequestParam(defaultValue = "0") Integer pageNo,
    @RequestParam(defaultValue = "2") Integer pageSize){
        return jobOpeningsService.getAllJobsPostedByRec(recid,pageNo,pageSize);
    }

    // addjob to job openings
    @PostMapping("/addjob")
    public JobOpeningsDTO create(@RequestBody AddJobReq req) throws  Exception{
        if(jobOpeningsService.create(req)!=null)
            return jobOpeningsService.create(req);
        else
            throw new Exception("Job cant be created");
    }

    // get all the seeker that applied for a job

    @GetMapping("/AllSeekerForJob/{jobid}")
    public PageDTO<SeekerDTO> AllSeekerForJob(@PathVariable(value = "jobid") Integer jobid, @RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "2") Integer pageSize) throws Exception{
        return jobAppliedService.findAllSeekerForJob(jobid,pageNo,pageSize);
    }

    //get all recruiter details by user id

    @GetMapping("/recdetails/{userid}")
    public RecruiterDTO recruiterDetails(@PathVariable(value = "userid") Integer userid){
        return recruiterService.getRecruiterDetails(userid);
    }

    // update job status posted by recruiter

    @PutMapping("/changeJobStatus/{recid}/{jobid}")
    public JobOpeningsDTO changeJobStatus(@PathVariable(value = "recid") Integer recid,
                                          @PathVariable(value = "jobid") Integer jobid) throws Exception{
        return jobOpeningsService.updateJobStatus(recid,jobid);
    }

    // update recruiter details

    @PutMapping("/updateRecDetails/{userid}")
    public RecruiterDTO updateRDetails(@PathVariable(value = "userid") Integer userid, @RequestBody ChangeRecruiterDetailsReq changeRecruiterDetailsReq) throws Exception{
        return recruiterService.updateRecruiterDetails(userid,changeRecruiterDetailsReq);
    }

    /*
    *  @PostMapping("/userSignup")
    private User create(@RequestBody CreateUserReq req){
        return userService.create(req);
    }
    * */

}
