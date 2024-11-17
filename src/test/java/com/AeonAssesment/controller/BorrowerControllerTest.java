package com.AeonAssesment.controller;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.common.exception.BaseException;
import com.AeonAssesment.common.model.RestResponse;
import com.AeonAssesment.enums.ErrorCode;
import com.AeonAssesment.model.BookReq;
import com.AeonAssesment.model.BookRespDTO;
import com.AeonAssesment.model.BorrowerReq;
import com.AeonAssesment.model.BorrowerRespDTO;
import com.AeonAssesment.service.BorrowerService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BorrowerControllerTest {

    @Mock
    private BorrowerService borrowerService;

    private BorrowerController borrowerController;

    @BeforeEach
    private void setup() {
        borrowerController = new BorrowerController(borrowerService);
    }

    @Test
    public void givenValidRequestAndActionTypeWhenProcessReturnResult()
    {
        BorrowerRespDTO borrowerRespDTO = new BorrowerRespDTO();
        borrowerRespDTO.setCreatedTime(OffsetDateTime.now());
        borrowerRespDTO.setStatus("A");
        borrowerRespDTO.setName("RandomName");
        borrowerRespDTO.setEmail("test@gmail.com");

        BorrowerReq borrowerReq = new BorrowerReq();
        borrowerReq.setName("RandomName");
        borrowerReq.setEmail("test@gmail.com");

        when(borrowerService.processBorrower(ActionType.CREATE,borrowerReq)).thenReturn(borrowerRespDTO);
        RestResponse<BorrowerRespDTO> borrowerRespDTORestResponse = borrowerController.process(ActionType.CREATE,borrowerReq);

        verify(borrowerService,times(1)).processBorrower(any(),any());
        Assertions.assertEquals(borrowerRespDTORestResponse.getData(),borrowerRespDTO);
    }

    @Test
    public void givenInvalidActionTypeWhenProcessThrowError()
    {
        BorrowerReq borrowerReq = new BorrowerReq();
        borrowerReq.setName("RandomName");
        borrowerReq.setEmail("test@gmail.com");

        when(borrowerService.processBorrower(ActionType.UPDATE,borrowerReq)).thenThrow(new ServiceException(ErrorCode.INVALID_ACTION_TYPE.toString()));
        Assertions.assertThrows(ServiceException.class, ()-> borrowerController.process(ActionType.UPDATE,borrowerReq));

        verify(borrowerService,times(1)).processBorrower(any(),any());
    }

    @Test
    public void givenInvalidEmailWhenProcessThrowError()
    {
        BorrowerReq borrowerReq = new BorrowerReq();
        borrowerReq.setName("RandomName");
        borrowerReq.setEmail("test@gmail");

        when(borrowerService.processBorrower(ActionType.CREATE,borrowerReq)).thenThrow(new ServiceException(ErrorCode.INVALID_EMAIL_EXCEPTION.toString()));
        Assertions.assertThrows(ServiceException.class, ()-> borrowerController.process(ActionType.CREATE,borrowerReq));

        verify(borrowerService,times(1)).processBorrower(any(),any());
    }

    @Test
    public void givenInvalidReqWhenProcessThrowError()
    {
        BorrowerReq borrowerReq = new BorrowerReq();
        borrowerReq.setName(null);
        borrowerReq.setEmail(null);

        Assertions.assertThrows(BaseException.class, ()-> borrowerController.process(ActionType.UPDATE,borrowerReq));
        verify(borrowerService,times(0)).processBorrower(any(),any());
    }
}
