package com.AeonAssesment.service;

import com.AeonAssesment.data.Borrower;
import com.AeonAssesment.data.BorrowerRepository;
import com.AeonAssesment.enums.ErrorCode;
import com.AeonAssesment.model.BorrowerReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowerValidateService {

    private final BorrowerRepository borrowerRepository;
    public void validateEmail(BorrowerReq borrowerReq)
    {
        EmailValidator validator = EmailValidator.getInstance();
        if(!validator.isValid(borrowerReq.getEmail()))
        {
            throw new RuntimeException(ErrorCode.INVALID_EMAIL_EXCEPTION.toString());
        }
        Optional<Borrower> borrower = borrowerRepository.retrieveUserByEmail(borrowerReq.getEmail());

        if(borrower.isPresent())
        {
            throw new RuntimeException(ErrorCode.INVALID_EMAIL_EXIST.toString());
        }

    }


}
