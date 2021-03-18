package com.wheelseye.ehiring.service;

import com.wheelseye.ehiring.entity.User;
import com.wheelseye.ehiring.entity.UserAccount;
import com.wheelseye.ehiring.repo.UserAccountRepo;
import com.wheelseye.ehiring.repo.UserRepo;
import com.wheelseye.ehiring.request.UserSigninReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private UserRepo userRepo;

    public User getUserByDetails(UserSigninReq userSigninReq) throws Exception{
        User user =  userRepo.findByEmailId(userSigninReq.getEmailId());
        //UserAccount userAccount = userAccountRepo.findByEmailId(userSigninReq.getEmailId());

        if(user!=null){
            UserAccount userAccount = userAccountRepo.findByUser(user);
            if(userAccount.getPassword().equals(userSigninReq.getPassword()) && userAccount.getType()==userSigninReq.getUserType())
                return user;
            throw new Exception("Incorrect Details");
        }
        throw new Exception("user doesnt exist");
    }

}
