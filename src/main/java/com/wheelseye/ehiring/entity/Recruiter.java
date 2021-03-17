package com.wheelseye.ehiring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "recruiters")
@Getter
@Setter
public class Recruiter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique = true,nullable = false)
    private Integer id;
/*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruiters")
    private List<JobOpenings> jobOpenings;
*/
    //Join the table recruiter and Userprofile
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "yoe")
    private Integer yoe;

    @Column(name = "company")
    private String company;


}
