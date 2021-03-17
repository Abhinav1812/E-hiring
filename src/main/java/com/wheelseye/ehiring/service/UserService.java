package com.wheelseye.ehiring.service;

import com.sun.istack.NotNull;
import com.wheelseye.ehiring.dto.PageDTO;
import com.wheelseye.ehiring.dto.SeekerDTO;
import com.wheelseye.ehiring.entity.Seeker;
import com.wheelseye.ehiring.entity.User;
import com.wheelseye.ehiring.entity.UserAccount;
import com.wheelseye.ehiring.repo.SeekerRepo;
import com.wheelseye.ehiring.repo.UserAccountRepo;
import com.wheelseye.ehiring.repo.UserRepo;
import com.wheelseye.ehiring.request.ChangePasswordReq;
import com.wheelseye.ehiring.request.CreateUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserAccountRepo userAccountRepo;

    public PageDTO<User> getAllUser(Integer pageNo,Integer pageSize) {
        Pageable pageable1 = PageRequest.of(pageNo,pageSize);
        Page<User> userPage= userRepo.findAll(pageable1);
        List<User> users = new ArrayList<>();
        //return userPage;
        for (User user : userPage.getContent()) {
            users.add(user);
        }

        return new PageDTO<User>(pageable1.getPageNumber(),pageable1.getPageSize(),(Long) userPage.getTotalElements(), users);
        //return userPage;
        //return userRepo.findAll();

    }


    public User create(CreateUserReq req){
        User user = new User();
        UserAccount userAccount = new UserAccount();
        user.setName(req.getName());
        user.setEmailId(req.getEmail_id());
        //user.setPassword(req.getPassword());
        user = userRepo.save(user);

        //userAccount.setName(req.getName());
        //userAccount.setEmailid(req.getEmail_id());
        userAccount.setPassword(req.getPassword());
        userAccount.setType(req.getType());
        userAccount.setUser(user);
        userAccountRepo.save(userAccount);

        return user;

    }

    public User getUserById(Integer id){
         return userRepo.findById(id).orElse(null);
    }

    public UserAccount updatePassword(Integer id, ChangePasswordReq changePasswordReq) throws Exception {
        User user = userRepo.findById(id).orElse(null);
        UserAccount userAccount = userAccountRepo.findByUser(user);
        if(!(userAccount.getPassword().equals(changePasswordReq.getOldPassword()))){
            throw new Exception("Incorrect Old Password");
        }
        userAccount.setPassword(changePasswordReq.getNewPassword());
        //System.out.println("Password Updated Successfully");

        return userAccountRepo.save(userAccount);
    }
}
