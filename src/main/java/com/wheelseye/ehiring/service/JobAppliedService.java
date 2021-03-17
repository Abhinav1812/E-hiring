package com.wheelseye.ehiring.service;

import com.wheelseye.ehiring.converter.JobAppliedConverter;
import com.wheelseye.ehiring.dto.JobAppliedDTO;
import com.wheelseye.ehiring.dto.JobOpeningsDTO;
import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.entity.JobApplied;
import com.wheelseye.ehiring.entity.JobOpenings;
import com.wheelseye.ehiring.entity.Seeker;
import com.wheelseye.ehiring.entity.User;
import com.wheelseye.ehiring.repo.JobAppliedRepo;
import com.wheelseye.ehiring.repo.JobOpeningsRepo;
import com.wheelseye.ehiring.repo.SeekerRepo;
import com.wheelseye.ehiring.request.ApplyJobByUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobAppliedService {

    @Autowired
    private JobAppliedRepo jobAppliedRepo;

    @Autowired
    private SeekerRepo seekerRepo;

    @Autowired
    private JobOpeningsRepo jobOpeningsRepo;


    public String applyforJob(ApplyJobByUserReq applyJobByUserReq){
        Seeker seeker = seekerRepo.findByUserId(applyJobByUserReq.getUserId());
        JobOpenings jobOpenings = jobOpeningsRepo.getJobByjobid(applyJobByUserReq.getJobId());
        JobApplied jobApplied = new JobApplied();
        jobApplied.setSeekerId(seeker);
        jobApplied.setStatus("In progress");
        jobApplied.setJobOpenings(jobOpenings);
        jobApplied.setAppliedOn(new Date());
        jobAppliedRepo.save(jobApplied);
        return "Successfully";
    }

    public PageDTO<JobAppliedDTO> getJobsAppliedByUser(Integer userid, Integer pageNo, Integer pageSize){

        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Seeker seeker = seekerRepo.getSeekerByUserId(userid);
        Page<JobApplied> jobApplieds = jobAppliedRepo.findJobsByUserId(seeker, pageable);
        List<JobAppliedDTO> jobAppliedDTOS = new ArrayList<>();
        for (JobApplied jobApplied : jobApplieds) {
            jobAppliedDTOS.add(JobAppliedConverter.converter(jobApplied));
        }
        return new PageDTO<JobAppliedDTO>(pageable.getPageNumber(),pageable.getPageSize(), (Long) jobApplieds.getTotalElements(),jobAppliedDTOS);
    }
}
