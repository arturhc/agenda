package com.sbcourse.agenda.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "service")
public class Service extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description_type")
    private String durationType;

}
