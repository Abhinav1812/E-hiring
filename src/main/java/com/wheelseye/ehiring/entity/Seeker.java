package com.wheelseye.ehiring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "seekers")
@Getter
@Setter
public class Seeker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id" , unique = true, nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "exp")
    private Integer exp;

    @Column(name = "link")
    private String link;

    @Column(name = "profile")
    private String profile;

    //@ManyToMany(cascade = CascadeType.ALL)


}
