package com.wheelseye.ehiring.request;

import com.wheelseye.ehiring.enumm.EntityTypeEnum1;
import lombok.Data;

@Data
public class UserSigninReq {
    private String emailId;
    private String password;
    private EntityTypeEnum1 userType;
}
