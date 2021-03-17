package com.wheelseye.ehiring.request;

import lombok.Data;

@Data
public class ApplyJobByUserReq {
    private Integer userId;
    private Integer jobId;
}

