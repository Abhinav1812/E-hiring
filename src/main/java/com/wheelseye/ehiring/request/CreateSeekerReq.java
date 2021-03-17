package com.wheelseye.ehiring.request;

import com.wheelseye.ehiring.enumm.EntityTypeEnum1;
import lombok.Data;

@Data
public class CreateSeekerReq {
    private Integer userId;
    private Integer exp;
    private String link;
    private String profile;
}
