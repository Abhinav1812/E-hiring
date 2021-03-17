package com.wheelseye.ehiring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wheelseye.ehiring.enumm.EntityTypeEnum1;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EntityTypeEnum1 type;

    /*
    @Column(name = "name")
    private String name;
    */
    @Column(name = "password")
    private String password;


}
