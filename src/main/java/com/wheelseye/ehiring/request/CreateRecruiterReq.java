package com.wheelseye.ehiring.request;

import lombok.Data;

@Data
public class CreateRecruiterReq {
    private Integer userId;
    private Integer yoe;
    private String company;
}

