package com.wheelseye.ehiring.entity;

import com.wheelseye.ehiring.enumm.EntityTypeEnum2;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "skill_maps")
@Getter
@Setter
public class SkillsMap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "skill_id")
    private Skills skills;

    @Column(name = "entity_type")
    @Enumerated(EnumType.STRING)
    private EntityTypeEnum2 entityType;

    @Column(name = "entity_id")
    private Integer entityId;


}
