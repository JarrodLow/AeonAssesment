package com.AeonAssesment.common.utils;

import com.AeonAssesment.common.exception.BaseException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
@UtilityClass
public class ValidateUtils {

    public static <T> void validate(T object)
    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        String errorMessage ="";
        if(!violations.isEmpty())
        {
            for(ConstraintViolation<?> violation : violations)
            {
                errorMessage =  violation.getPropertyPath().toString() + " " + violation.getMessage();

            }
            throw new BaseException(errorMessage);
        }
    }

    public static <T> void validate(T object,Class validateClass)
    {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object,validateClass);
        String errorMessage ="";
        if(!violations.isEmpty())
        {
            for(ConstraintViolation<?> violation : violations)
            {
                errorMessage =  violation.getPropertyPath().toString() + " " + violation.getMessage();
            }
            throw new BaseException(errorMessage);
        }
    }
}
