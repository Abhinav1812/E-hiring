package com.wheelseye.ehiring.converter;

import com.wheelseye.ehiring.dto.JobOpeningsDTO;
import com.wheelseye.ehiring.entity.JobOpenings;

public class JobOpeningsConverter {
    public static JobOpeningsDTO converter(JobOpenings jobOpenings){
        JobOpeningsDTO jobOpeningsDTO  = new JobOpeningsDTO();
        jobOpeningsDTO.setJobId(jobOpenings.getId());
        jobOpeningsDTO.setProfile(jobOpenings.getProfile());
        jobOpeningsDTO.setExp(jobOpenings.getExp());
        jobOpeningsDTO.setCreatedAt(jobOpenings.getCreatedAt());
        jobOpeningsDTO.setStatus(jobOpenings.getStatus());

        return jobOpeningsDTO;
    }
}
