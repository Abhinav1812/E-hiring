package com.wheelseye.ehiring.request;

import lombok.Data;

@Data
public class ChangePasswordReq {
    private String oldPassword;
    private String newPassword;
}

