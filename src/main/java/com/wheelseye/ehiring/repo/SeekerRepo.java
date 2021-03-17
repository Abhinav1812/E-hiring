package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.dto.JobAppliedDTO;
import com.wheelseye.ehiring.entity.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeekerRepo extends JpaRepository<Seeker,Integer>{
    //Seeker findSeekerById(Integer id);
    @Query("SELECT S from Seeker S where S.user.id = ?1")
    Seeker getSeekerByUserId(Integer id);

    Seeker findByUserId(Integer id);

}
