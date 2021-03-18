package com.wheelseye.ehiring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wheelseye.ehiring.entity.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SeekerDTO {
    private Integer seekerId;
    private Integer exp ;
    private String link;
    private String profile;
}
