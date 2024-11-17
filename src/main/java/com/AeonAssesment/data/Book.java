package com.AeonAssesment.data;

import com.AeonAssesment.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "book")
public class Book extends BaseEntity {

    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

}
