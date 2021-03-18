package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.entity.JobApplied;
import com.wheelseye.ehiring.entity.Seeker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface JobAppliedRepo extends JpaRepository<JobApplied,Integer> {

    @Query("Select J from JobApplied J where J.seekerId = ?1 order by J.appliedOn")
    Page<JobApplied> findJobsByUserId(Seeker seeker, Pageable pageable);

    @Query("SELECT J from JobApplied J where J.jobOpenings.id in ?1 order by J.appliedOn")
    Page<JobApplied> findSeekersByJobId(Integer jobid, Pageable pageable);

}
