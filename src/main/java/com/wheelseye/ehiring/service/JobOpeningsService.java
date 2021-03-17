package com.wheelseye.ehiring.service;

import com.wheelseye.ehiring.converter.JobOpeningsConverter;
import com.wheelseye.ehiring.dto.JobOpeningsDTO;
import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.entity.JobOpenings;
import com.wheelseye.ehiring.entity.Recruiter;
import com.wheelseye.ehiring.repo.*;
import com.wheelseye.ehiring.request.AddJobReq;
import com.wheelseye.ehiring.request.SearchJobWithSkillReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobOpeningsService {

    @Autowired
    private JobOpeningsRepo jobOpeningsRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RecruiterRepo recruiterRepo;

    @Autowired
    private SkillMapedRepo skillMapedRepo;

    public JobOpenings create(AddJobReq req){

        Optional<Recruiter> recruiter = recruiterRepo.findById(req.getRecId());
        Recruiter recruiter1 = recruiter.get();

        JobOpenings jobOpenings = new JobOpenings();
        jobOpenings.setRecruiters(recruiter1);
        jobOpenings.setExp(req.getExp());
        jobOpenings.setProfile(req.getProfile());
        jobOpenings.setStatus(req.getStatus());
        jobOpenings.setCreatedAt(new Date());
        return jobOpeningsRepo.save(jobOpenings);
    }

    public PageDTO<JobOpeningsDTO> getAllJobs(Integer pageNo,Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<JobOpenings> jobOpenings =  jobOpeningsRepo.findAll(pageable);

        List<JobOpeningsDTO> jobOpeningsDTOS= new ArrayList<>();
        for (JobOpenings jobOpening : jobOpenings.getContent()) {
            jobOpeningsDTOS.add(JobOpeningsConverter.converter(jobOpening));
        }

        return new PageDTO<JobOpeningsDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) jobOpenings.getTotalElements(),jobOpeningsDTOS);
    }

    public PageDTO<JobOpeningsDTO>  getAllJobsPostedByRec(Integer id, Integer pageNo, Integer pageSize){

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Optional<Recruiter> recruiter = recruiterRepo.findById(id);
        Page<JobOpenings> jobOpenings = jobOpeningsRepo.findJobsByRecId(recruiter,pageable);
        List<JobOpeningsDTO> jobOpeningsDTOS= new ArrayList<>();
        for (JobOpenings jobOpening : jobOpenings) {
            jobOpeningsDTOS.add(JobOpeningsConverter.converter(jobOpening));
        }
        return new PageDTO<JobOpeningsDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) jobOpenings.getTotalElements(),jobOpeningsDTOS);
    }



    public PageDTO<JobOpeningsDTO> getAllJobsAvailableByProfile(String profile,Integer pageNo,Integer pageSize){

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<JobOpenings> jobOpenings = jobOpeningsRepo.getAllJobsAvailableByProfile(profile,pageable);
        List<JobOpeningsDTO> jobOpeningsDTOS= new ArrayList<>();

        for (JobOpenings jobOpening : jobOpenings) {
            jobOpeningsDTOS.add(JobOpeningsConverter.converter(jobOpening));
        }

        return new PageDTO<JobOpeningsDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) jobOpenings.getTotalElements(),jobOpeningsDTOS);
    }


    public PageDTO<JobOpeningsDTO> getAllJobsAvailableBySkills(List<Integer> skillIds , Integer pageNo, Integer pageSize){

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Set<Integer> jobsId = skillMapedRepo.getAllJobIdBySkillIds(skillIds);

        Page<JobOpenings> jobOpenings = jobOpeningsRepo.getAllJobsAvailableByJobIds(jobsId,pageable);
        List<JobOpeningsDTO> jobOpeningsDTOS= new ArrayList<>();
        for (JobOpenings jobOpening : jobOpenings) {
            jobOpeningsDTOS.add(JobOpeningsConverter.converter(jobOpening));
        }

        return new PageDTO<JobOpeningsDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) jobOpenings.getTotalElements(),jobOpeningsDTOS);
    }

    /*
    public PageDTO<JobOpeningsDTO> getAllJobsAvailableBySkill(Integer skillid,Integer pageNo,Integer pageSize){

        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Set<Integer> jobId = skillMapedRepo.getAllJobIdBySkillId(skillid);
        Page<JobOpenings> jobOpenings = jobOpeningsRepo.getAllJobsAvailableByJobIds(jobId,pageable);
        List<JobOpeningsDTO> jobOpeningsDTOS= new ArrayList<>();
        for (JobOpenings jobOpening : jobOpenings) {
            jobOpeningsDTOS.add(JobOpeningsConverter.converter(jobOpening));
        }

        return new PageDTO<JobOpeningsDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) jobOpenings.getTotalElements(),jobOpeningsDTOS);
    }
    */

}
