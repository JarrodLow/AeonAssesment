package com.AeonAssesment.data;

import com.AeonAssesment.common.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigInteger;
import java.util.UUID;

@Data
@Entity(name = "book_detail")
public class BookDetail extends BaseEntity {

    @Column(name = "borrower_ID")
    private Integer borrowerID;

    @Column(name = "book_ID")
    private Integer bookID;

}
