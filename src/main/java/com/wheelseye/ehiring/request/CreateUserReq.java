package com.wheelseye.ehiring.request;


import com.wheelseye.ehiring.enumm.EntityTypeEnum1;
import lombok.Data;

@Data
public class CreateUserReq {
    private String name;
    private String email_id;
    private String password;
    private EntityTypeEnum1 type;


}
