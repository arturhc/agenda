package com.sbcourse.agenda.persistence.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "productService")
public class ProductService extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "duration_type")
    private String durationType;

}
