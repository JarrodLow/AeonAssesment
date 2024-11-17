package com.AeonAssesment.common.entity;

import com.AeonAssesment.common.listener.BaseEntityListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public class BaseEntity implements Serializable {
    @Id
    @Column(
            name = "id"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    @Column(
            name = "ver"
    )
    private int version;

    @Column(
            name = "created_by",
            updatable = false
    )
    private String createdBy;

    @Column(
            name = "created_dt",
            updatable = false
    )
    private OffsetDateTime createdTime;

    @Column(
            name = "updated_by",
            insertable = false
    )
    private String updatedBy;

    @Column(
            name = "updated_dt",
            insertable = false
    )
    private OffsetDateTime updatedTime;
    @Column(
            name = "sts_cd"
    )
    private String status;
}
