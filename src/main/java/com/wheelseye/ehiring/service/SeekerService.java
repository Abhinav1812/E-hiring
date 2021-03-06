package com.wheelseye.ehiring.service;

import com.wheelseye.ehiring.converter.JobOpeningsConverter;
import com.wheelseye.ehiring.converter.SeekerConverter;
import com.wheelseye.ehiring.dto.JobOpeningsDTO;
import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.dto.SeekerDTO;
import com.wheelseye.ehiring.entity.*;
import com.wheelseye.ehiring.enumm.EntityTypeEnum1;
import com.wheelseye.ehiring.enumm.EntityTypeEnum2;
import com.wheelseye.ehiring.repo.*;
import com.wheelseye.ehiring.request.ChangePasswordReq;
import com.wheelseye.ehiring.request.ChangeSeekerDetailsReq;
import com.wheelseye.ehiring.request.CreateSeekerReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SeekerService {
    @Autowired
    private SeekerRepo seekerRepo;

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JobOpeningsRepo jobOpeningsRepo;

    @Autowired
    private SkillMapedRepo skillMapedRepo;

    @Autowired
    private SkillsRepo skillsRepo;

    public PageDTO<SeekerDTO> getAllSeeker(Integer pageNo,Integer pageSize){

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Seeker> seekers = seekerRepo.findAll(pageable);
        List<SeekerDTO> seekerDTOS = new ArrayList<>();

        for (Seeker seeker : seekers.getContent()) {
            seekerDTOS.add(SeekerConverter.converter(seeker));
        }
        return new PageDTO<SeekerDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) seekers.getTotalElements(),seekerDTOS);

    }

    public SeekerDTO create(CreateSeekerReq req) throws Exception{

        Optional<User> optionalUser = userRepo.findById(req.getUserId());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserAccount userAccount = userAccountRepo.findByUser(user);
            if(null != userAccount) {
                if (userAccount.getType() == EntityTypeEnum1.SEEKER) {
                    Seeker seeker = new Seeker();
                    seeker.setExp(req.getExp());
                    seeker.setUser(user);
                    seeker.setLink(req.getLink());
                    seeker.setProfile(req.getProfile());
                    seekerRepo.save(seeker);
                    return SeekerConverter.converter(seeker);
                }
                throw new Exception("Seeker cant be created");
            }
            throw new Exception("user account doesnt exist");
        }
        throw new Exception("user doesnt exist");
    }

    //get all jobs available for user

    public PageDTO<JobOpeningsDTO> getJobsAvailableForUser(Integer id){

        Pageable pageable = PageRequest.of(0,2);


        Seeker seeker = seekerRepo.getSeekerByUserId(id);
        Set<Integer> skillIds = skillMapedRepo.getSkillIdsByUserId(seeker.getId());
        Set<Integer> jobIds = skillMapedRepo.getAllJobIdBySkillIds(skillIds);

        Page<JobOpenings> jobOpeningsavailable= jobOpeningsRepo.findByIdIn(jobIds,pageable);


        List<JobOpeningsDTO> jobOpeningsDTOS= new ArrayList<>();
        for (JobOpenings jobOpenings : jobOpeningsavailable) {
            jobOpeningsDTOS.add(JobOpeningsConverter.converter(jobOpenings));
        }

        return new PageDTO<JobOpeningsDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) jobOpeningsavailable.getTotalElements(),jobOpeningsDTOS);

        /*
        Seeker seeker = seekerRepo.getSeekerByUserId(id);
        Pageable pageable = PageRequest.of(0,2);
        Page<JobOpenings> jobOpenings = jobOpeningsRepo.getAllJobsAvailableByProfile(seeker.getProfile(),pageable);

        List<JobOpeningsDTO> jobOpeningsDTOS = new ArrayList<>();
        for (JobOpenings jobOpening : jobOpenings) {
            jobOpeningsDTOS.add(JobOpeningsConverter.converter(jobOpening));
        }

        return new PageDTO<JobOpeningsDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) jobOpenings.getTotalElements(),jobOpeningsDTOS);
        */
    }


    public String addSkillForSeeker(Integer skillid , Integer seekerid){
        Optional<Skills> skill= skillsRepo.findById(skillid);
        if(!skill.isPresent())
            return "skill is not available";
        else{
            SkillsMap skillsMap = new SkillsMap();
            skillsMap.setEntityType(EntityTypeEnum2.SEEK);
            skillsMap.setEntityId(seekerid);
            skillsMap.setSkills(skill.get());
            skillMapedRepo.save(skillsMap);
            return "skill addded for user";
        }
    }
    public SeekerDTO getSeekerDetails(Integer id) throws Exception{

        Seeker seeker = seekerRepo.findByUserId(id);
        if (seeker==null)
            throw new Exception("seeker doesnt exist");
        else
            return SeekerConverter.converter(seeker);
    }


    public SeekerDTO updateSeekerDetails(Integer id, ChangeSeekerDetailsReq changeSeekerDetailsReq) throws Exception{
        Seeker seeker  = seekerRepo.getSeekerByUserId(id);
        if(seeker== null)
            throw new Exception("Seeker not found");
        if(changeSeekerDetailsReq.getExp()!=null)
            seeker.setExp(changeSeekerDetailsReq.getExp());
        if(changeSeekerDetailsReq.getLink()!=null)
            seeker.setLink(changeSeekerDetailsReq.getLink());
        if(changeSeekerDetailsReq.getProfile()!=null)
            seeker.setProfile(changeSeekerDetailsReq.getProfile());

        return SeekerConverter.converter(seekerRepo.save(seeker));
    }

}
