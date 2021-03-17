package com.wheelseye.ehiring.controller;

import com.wheelseye.ehiring.dto.JobOpeningsDTO;
import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.entity.JobOpenings;
import com.wheelseye.ehiring.request.SearchJobWithSkillReq;
import com.wheelseye.ehiring.service.JobOpeningsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobSearchController {

    @Autowired
    private JobOpeningsService jobOpeningsService;


    @GetMapping("/alljobs")
    private PageDTO<JobOpeningsDTO> getAllJobs(@RequestParam(defaultValue = "0") Integer pageNo,@RequestParam(defaultValue = "2") Integer pageSize){
        return jobOpeningsService.getAllJobs(pageNo,pageSize);
    }

    @GetMapping("/searchjobprof/{profile}")
    private PageDTO<JobOpeningsDTO> searchAllJobsWithProfile(@RequestParam(defaultValue = "0") Integer pageNo , @RequestParam(defaultValue = "3")
            Integer pageSize,@PathVariable(value = "profile") String profile){
        return jobOpeningsService.getAllJobsAvailableByProfile(profile,pageNo,pageSize);
    }


    @GetMapping("/searchjobskills")
    private PageDTO<JobOpeningsDTO> searchAllJobsWithSkills(@RequestParam(defaultValue = "0") Integer pageNo , @RequestParam(defaultValue = "2")
            Integer pageSize,@RequestParam(value = "skillids") List<Integer> skillIds) {
        return jobOpeningsService.getAllJobsAvailableBySkills(skillIds,pageNo,pageSize) ;
    }

    /*
    @GetMapping("/searchjobskill/{skillid}")
    private PageDTO<JobOpeningsDTO> searchAllJobWithSkill(@RequestParam(defaultValue = "0") Integer pageNo , @RequestParam(defaultValue = "3")
       Integer pageSize, @PathVariable(value = "skillid") Integer skillid ){
        return  jobOpeningsService.getAllJobsAvailableBySkill(skillid,pageNo,pageSize);
    }
    */
}
