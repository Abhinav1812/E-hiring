package com.wheelseye.ehiring.repo;

import com.wheelseye.ehiring.entity.User;
import com.wheelseye.ehiring.entity.UserAccount;
import com.wheelseye.ehiring.enumm.EntityTypeEnum1;
import com.wheelseye.ehiring.request.UserSigninReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount,Integer> {

    UserAccount findByUser(User user);

    UserAccount findByUserAndType(User user, EntityTypeEnum1 entityTypeEnum1);
}
