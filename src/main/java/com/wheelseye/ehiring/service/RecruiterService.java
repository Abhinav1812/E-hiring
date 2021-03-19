package com.wheelseye.ehiring.service;

import com.wheelseye.ehiring.converter.RecruiterConverter;
import com.wheelseye.ehiring.converter.SeekerConverter;
import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.dto.RecruiterDTO;
import com.wheelseye.ehiring.dto.SeekerDTO;
import com.wheelseye.ehiring.entity.*;
import com.wheelseye.ehiring.enumm.EntityTypeEnum1;
import com.wheelseye.ehiring.enumm.EntityTypeEnum2;
import com.wheelseye.ehiring.repo.*;
import com.wheelseye.ehiring.request.ChangeRecruiterDetailsReq;
import com.wheelseye.ehiring.request.ChangeSeekerDetailsReq;
import com.wheelseye.ehiring.request.CreateRecruiterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepo recruiterRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private SkillsRepo skillsRepo;

    @Autowired
    private SkillMapedRepo skillMapedRepo;

    public PageDTO<RecruiterDTO> getAllRecruiter(Integer pageNo, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Recruiter> recruiter = recruiterRepo.findAll(pageable);
        List< RecruiterDTO > recruiterDTOS = new ArrayList<>();

        for (Recruiter recruiter1 : recruiter.getContent()) {
            recruiterDTOS.add(RecruiterConverter.converter(recruiter1));
        }
        return new PageDTO<RecruiterDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) recruiter.getTotalElements(),recruiterDTOS);

    }

    /*
    public List<Seeker> getAllSeeker(){
        return seekerRepo.findAll();
    }
    */
    public RecruiterDTO create(CreateRecruiterReq req) throws Exception{

        Optional<User> optionalUser = userRepo.findById((req.getUserId()));
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            UserAccount userAccount = userAccountRepo.findByUserAndType(user,EntityTypeEnum1.RECRUITER);
            if(null!=userAccount){

                    Recruiter recruiter = new Recruiter();
                    recruiter.setUser(user);
                    recruiter.setYoe(req.getYoe());
                    recruiter.setCompany(req.getCompany());
                    return RecruiterConverter.converter(recruiterRepo.save(recruiter));

            }
            else
                throw  new Exception("User is already a recruiter");
        }
        else
            throw new Exception("user doesnt exist");
    }

    public RecruiterDTO getRecruiterDetails(Integer id){

        Recruiter recruiter = recruiterRepo.getRecruiterByUserId(id);
        return RecruiterConverter.converter(recruiter);
    }

    // update recruiter details


    public String addSkillForJob(Integer skillid, Integer jobid){
        Optional<Skills> skill= skillsRepo.findById(skillid);

        if(!skill.isPresent())
            return "skill or job is not available";
        else{
            SkillsMap skillsMap = new SkillsMap();
            skillsMap.setEntityType(EntityTypeEnum2.JOB);
            skillsMap.setEntityId(jobid);
            skillsMap.setSkills(skill.get());
            skillMapedRepo.save(skillsMap);
            return "skill addded for job";
        }
    }
    public RecruiterDTO updateRecruiterDetails(Integer id, ChangeRecruiterDetailsReq changeRecruiterDetailsReq) throws Exception{
        Recruiter recruiter  = recruiterRepo.getRecruiterByUserId(id);
        if(recruiter== null)
            throw new Exception("Recruiter not found");
        if(changeRecruiterDetailsReq.getYoe()!=null)
            recruiter.setYoe(changeRecruiterDetailsReq.getYoe());

        if(changeRecruiterDetailsReq.getCompany()!=null)
            recruiter.setCompany(changeRecruiterDetailsReq.getCompany());

        return RecruiterConverter.converter(recruiterRepo.save(recruiter));
    }
}
