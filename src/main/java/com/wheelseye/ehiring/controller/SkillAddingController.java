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


    @PostMapping("/addskillseeker/{seekerid}/{skillname}")
    private String addSkills(@PathVariable(value = "skillname")String skillname, @PathVariable(value = "seekerid") Integer seekerid) throws Exception{
        if(!(seekerService.addSkillForSeeker(skillname, seekerid).equals("skill is not available")))
            return "Skill Added to user";
        else
            return "Cant Add Skill";
    }

    @PostMapping("/addskilljob/{jobid}/{skillname}")
    private String addSkillsToJob(@PathVariable(value = "skillname")String skillname, @PathVariable(value = "jobid") Integer jobid){
        if(recruiterService.addSkillForJob(skillname, jobid).equals("skill is not available"))
            return "skill is not available";
        else
            return "skill added to job";
    }
}
