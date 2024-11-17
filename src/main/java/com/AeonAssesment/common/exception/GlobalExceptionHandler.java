package com.AeonAssesment.common.exception;

import com.AeonAssesment.common.enums.ResponseStatus;
import com.AeonAssesment.common.model.BaseRestResponse;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<BaseRestResponse> handleServiceException(ServiceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseRestResponse(ResponseStatus.FAILURE.getStatus(), ex.getMessage(), null));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseRestResponse> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseRestResponse(ResponseStatus.FAILURE.getStatus(), ex.getMessage(), null));
    }
}
