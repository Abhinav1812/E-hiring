package com.wheelseye.ehiring.converter;

import com.wheelseye.ehiring.dto.JobAppliedDTO;
import com.wheelseye.ehiring.entity.JobApplied;

public class JobAppliedConverter {
    public static JobAppliedDTO converter(JobApplied jobApplied){
        JobAppliedDTO jobAppliedDTO  = new JobAppliedDTO();
        jobAppliedDTO.setAppliedDate(jobApplied.getAppliedOn());
        jobAppliedDTO.setStatus(jobApplied.getStatus());
        jobAppliedDTO.setJob_id(jobApplied.getId());

        return jobAppliedDTO;
    }
}
