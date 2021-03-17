package com.wheelseye.ehiring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "job_openings")
@Getter
@Setter
public class JobOpenings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true,nullable = false)
    private Integer id;

    //recruiter posting multiple jobs

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rec_id")
    private Recruiter recruiters;

    @Column(name = "exp")
    private String exp;

    @Column(name = "profile")
    private String profile;

    @Column(name = "status")
    private String status;

    @Column(name = "createdat")
    private Date createdAt;


}
