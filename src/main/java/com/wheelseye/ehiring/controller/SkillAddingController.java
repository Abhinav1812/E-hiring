package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.service.RecruiterService;
import com.wheelseye.ehiring.service.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillAddingController {

    @Autowired
    private SeekerService seekerService;

    @Autowired
    private RecruiterService recruiterService;


    // add skill for user by skill id
    @PostMapping("/addskillseeker/{seekerid}/{skillid}")
    public String addSkills(@PathVariable(value = "seekerid") Integer seekerid,@PathVariable(value = "skillid")Integer skillid) throws Exception{
        if(!(seekerService.addSkillForSeeker(skillid, seekerid).equals("skill is not available")))
            return "Skill Added to user";
        else
            return "Cant Add Skill either the seeker or skill doesnt exist";
    }

    //add skill for job by skill id
    @PostMapping("/addskilljob/{jobid}/{skillid}")
    public String addSkillsToJob(@PathVariable(value = "skillid")Integer skillid, @PathVariable(value = "jobid") Integer jobid){
        if(recruiterService.addSkillForJob(skillid, jobid).equals("skill is not available"))
            return "skill is not available";
        else
            return "skill added to job";
    }
}
