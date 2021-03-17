package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sun.jvm.hotspot.debugger.Page;

@Repository

public interface UserRepo extends JpaRepository<User,Integer> {


    User findUserById(Integer id);
    User findByEmailId(String emailId);


}
