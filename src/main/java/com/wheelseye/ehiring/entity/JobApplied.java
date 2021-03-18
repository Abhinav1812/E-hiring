package com.wheelseye.ehiring.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "job_applied")
@Getter
@Setter
public class JobApplied implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true,nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seeker_id")
    private Seeker seekerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private JobOpenings jobOpenings;

    /*
    @Column(name = "job_id")
    private Integer job_id;
    */
    @Column(name = "status")
    private String status;

    @Column(name = "appliedon")
    private Date appliedOn;


}
