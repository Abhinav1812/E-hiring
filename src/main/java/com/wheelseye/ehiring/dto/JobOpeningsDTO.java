package com.wheelseye.ehiring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;


@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JobOpeningsDTO {
    private Integer jobId;
    private String exp;
    private String status;
    private String profile;
    private Date  createdAt;
}
