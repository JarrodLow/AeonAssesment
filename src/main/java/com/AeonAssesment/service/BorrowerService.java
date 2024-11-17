package com.AeonAssesment.service;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.data.Borrower;
import com.AeonAssesment.data.BorrowerRepository;
import com.AeonAssesment.enums.ErrorCode;
import com.AeonAssesment.model.BorrowerReq;
import com.AeonAssesment.model.BorrowerRespDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BorrowerValidateService borrowerValidateService;
    private final ObjectMapper objectMapper;
    @Transactional
    public BorrowerRespDTO processBorrower(ActionType actionType, BorrowerReq borrowerReq)
    {
        if(actionType.equals(ActionType.CREATE))
        {
            borrowerValidateService.validateEmail(borrowerReq);
            Borrower newBorrower = objectMapper.convertValue(borrowerReq,Borrower.class);
            Borrower borrowerResp = borrowerRepository.saveAndFlush(newBorrower);
            return BorrowerRespDTO.fromEntityToDTO(borrowerResp);
        }
        throw new ServiceException(ErrorCode.INVALID_ACTION_TYPE.toString());

    }
}
