package com.AeonAssesment.common.listener;


import com.AeonAssesment.common.entity.BaseEntity;
import com.AeonAssesment.common.enums.StatusCode;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.OffsetDateTime;

@Component
public class BaseEntityListener {
    public BaseEntityListener(){}

    @PrePersist
    public void onPrePersist(BaseEntity baseEntity) {
        baseEntity.setCreatedBy("sys");
        baseEntity.setCreatedTime(OffsetDateTime.now());
        baseEntity.setVersion(0);
        baseEntity.setStatus(StatusCode.ACTIVE.toValue());
    }

    @PreUpdate
    public void onPreUpdate(BaseEntity baseEntity) {
        baseEntity.setUpdatedBy("sys");
        baseEntity.setUpdatedTime(OffsetDateTime.now());
    }
}
