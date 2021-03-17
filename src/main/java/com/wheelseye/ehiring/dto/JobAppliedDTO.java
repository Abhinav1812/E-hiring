package com.wheelseye.ehiring.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class JobAppliedDTO {
    private Integer job_id;
    private String status;
    private Date appliedDate;
}
