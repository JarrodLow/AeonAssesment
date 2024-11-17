package com.AeonAssesment.common.model;

import com.AeonAssesment.common.enums.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class RestResponse<T> {
    private String status;
    private T data;
    private String description;


    public static <T> RestResponse<T> success(T data)
    {
        return new RestResponse<>(ResponseStatus.SUCCESS.getStatus(),data, null);
    }

    public static<T> RestResponse<T> failure(String description)
    {
        return new RestResponse<>(ResponseStatus.FAILURE.getStatus(), null,description);
    }

    public static boolean isStatusSuccess(String status) {return "Success".equals(status);}

    public static boolean isStatusFailed(String status) {return !isStatusSuccess(status);}

}
