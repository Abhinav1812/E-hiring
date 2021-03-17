package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.entity.JobOpenings;
import com.wheelseye.ehiring.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepo extends JpaRepository<Recruiter,Integer> {
    //Recruiter findRecruiterByUId(Integer id);
    //@Query("SELECT * FROM JobOpenings J RIGHT JOIN Recruiter R ON J.rec_id = R.id WHERE rec_id=:id", nativeQuery = true)


    @Query("SELECT R from Recruiter R where R.user.id= ?1")
    Recruiter getRecruiterByUserId(Integer id);

}
