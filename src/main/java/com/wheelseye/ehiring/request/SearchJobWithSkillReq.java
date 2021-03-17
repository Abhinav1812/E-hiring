package com.wheelseye.ehiring.request;

import com.wheelseye.ehiring.entity.Skills;
import lombok.Data;

import java.util.List;

@Data
public class SearchJobWithSkillReq {
    private List<Integer> skillsId;
}
