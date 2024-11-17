package com.AeonAssesment.common.enums;

import com.AeonAssesment.common.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;
import java.util.stream.Stream;

public enum StatusCode {
    @JsonProperty("A")
    ACTIVE("A"),

    @JsonProperty("I")
    INACTIVE("I"),
    @JsonProperty("D")
    DELETED("D");

    private final String value;

    @JsonValue
    public String toValue(){return this.getValue();}

    @JsonCreator
    public static StatusCode forValue(String code) {
       StatusCode result =(StatusCode)Stream.of(values()).filter((e)->{
            return e.value.equals(code);
        }).findFirst().orElse(null);
       if (Objects.isNull(result))
       {
           throw new BaseException("Unknown Value : " + code);
       }
       return result;
    }

    private StatusCode(final String value){this.value = value;}

    private String getValue(){return this.value;}
}
