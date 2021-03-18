package com.wheelseye.ehiring.converter;

import com.wheelseye.ehiring.dto.RecruiterDTO;
import com.wheelseye.ehiring.entity.Recruiter;

public class RecruiterConverter {

    public static RecruiterDTO converter(Recruiter recruiter){
        RecruiterDTO recruiterDTO = new RecruiterDTO();
        recruiterDTO.setCompany(recruiter.getCompany());
        recruiterDTO.setYoe(recruiter.getYoe());
        recruiterDTO.setRecId(recruiter.getId());

        return  recruiterDTO;
    }
}
