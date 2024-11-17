package com.AeonAssesment.common.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    FAILURE("FAILED"),
    PENDING("PENDING"),
    SUCCESS("SUCCESS");

    private final String status;

    ResponseStatus(String status){this.status = status;}
}
