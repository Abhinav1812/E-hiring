package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.entity.JobOpenings;
import com.wheelseye.ehiring.entity.Recruiter;
import com.wheelseye.ehiring.request.SearchJobWithSkillReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JobOpeningsRepo extends JpaRepository<JobOpenings,Integer> {
    @Query("select J from JobOpenings J where J.recruiters = ?1 order by J.createdAt")
    Page<JobOpenings> findJobsByRecId(Optional<Recruiter> recruiter, Pageable pageable);


    @Query("SELECT J from JobOpenings J where J.id = ?1 order by J.createdAt")
    List<JobOpenings> getAllJobsAvailableByJobId(Integer id);
    

    @Query("SELECT J from JobOpenings J where  J.profile = ?1 order by J.createdAt")
    Page<JobOpenings> getAllJobsAvailableByProfile(String profile, Pageable pageable);

    @Query("SELECT J from JobOpenings J where J.id in ?1 order by J.createdAt")
    Page<JobOpenings> getAllJobsAvailableByJobIds(Collection<Integer> jobid, Pageable pageable);


    @Query("SELECT J from JobOpenings J where J.id  = ?1")
    JobOpenings getJobByjobid(Integer id);


}
