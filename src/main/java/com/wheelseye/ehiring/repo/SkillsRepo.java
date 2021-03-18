package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepo extends JpaRepository<Skills,Integer> {
    @Query("SELECT S from Skills S where S.skillName =?1")
    Skills getIdBySkillName(String skillname);


}
