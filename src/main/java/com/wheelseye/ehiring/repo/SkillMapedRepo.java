package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.entity.SkillsMap;
import com.wheelseye.ehiring.request.SearchJobWithSkillReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface SkillMapedRepo extends JpaRepository<SkillsMap,Integer> {

    //doubt how to use enum in query

    @Query("SELECT S from SkillsMap S where S.entityId = ?1 and S.entityType = 'SEEK'")
    List<SkillsMap> getSkillsByUserId(Integer id);

    @Query("SELECT distinct S.skills.id from SkillsMap S where S.entityId = ?1 and S.entityType = 'SEEK'")
    Set<Integer> getSkillIdsByUserId(Integer id);

    @Query("SELECT distinct S.entityId from SkillsMap S where S.entityType = 'JOB' and S.skills.id in ?1")
    Set<Integer> getAllJobIdBySkillIds(Collection<Integer> ids);

    /*
    @Query("SELECT distinct S.entityId from SkillsMap S where S.entityType = 'JOB' and S.skills.id in ?1")
    Set<Integer> getAllJobsAvailableBySkils(Collection<Integer> skillsIds);
    */
    /*
    @Query("select distinct S.entityId from SkillsMap S where S.entityType = 'JOB'and S.skills.id = ?1")
    Set<Integer> getAllJobIdBySkillId(Integer id);
    */

}
