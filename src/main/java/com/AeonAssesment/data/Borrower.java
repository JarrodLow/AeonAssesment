package com.AeonAssesment.data;

import com.AeonAssesment.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "borrower")
public class Borrower extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;
}
