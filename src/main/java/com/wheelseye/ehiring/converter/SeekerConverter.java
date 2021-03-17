package com.wheelseye.ehiring.converter;

import com.wheelseye.ehiring.dto.SeekerDTO;
import com.wheelseye.ehiring.entity.Seeker;

public class SeekerConverter {

    public static SeekerDTO converter(Seeker seeker){
        SeekerDTO seekerDTO = new SeekerDTO();
        seekerDTO.setExp(seeker.getExp());
        seekerDTO.setLink(seeker.getLink());
        seekerDTO.setProfile(seeker.getProfile());
        seekerDTO.setUserId(seeker.getId());
        return seekerDTO;
    }
}
