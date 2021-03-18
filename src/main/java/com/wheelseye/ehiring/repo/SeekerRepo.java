package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.dto.JobAppliedDTO;
import com.wheelseye.ehiring.entity.Seeker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface SeekerRepo extends JpaRepository<Seeker,Integer>{
    //Seeker findSeekerById(Integer id);
    @Query("SELECT S from Seeker S where S.user.id = ?1")
    Seeker getSeekerByUserId(Integer id);

    @Query("SELECT S from Seeker S where S.id in ?1 ")
    List<Seeker> findAllSeekerById(Collection<Integer> ids);

    Seeker findByUserId(Integer id);

}
