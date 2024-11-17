package com.AeonAssesment.common.exception;

import com.AeonAssesment.common.enums.ResponseStatus;
import com.AeonAssesment.common.model.BaseRestResponse;
import com.AeonAssesment.common.model.RestResponse;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public RestResponse<BaseRestResponse> handleServiceException(ServiceException ex) {
        return RestResponse.failure(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public RestResponse<BaseRestResponse> handleRuntimeException(RuntimeException ex) {
        return RestResponse.failure(ex.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public RestResponse<BaseRestResponse> handleBaseException(RuntimeException ex) {
        return RestResponse.failure(ex.getMessage());
    }
}
