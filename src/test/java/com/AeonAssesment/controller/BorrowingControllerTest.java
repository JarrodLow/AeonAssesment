package com.AeonAssesment.controller;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.common.exception.BaseException;
import com.AeonAssesment.common.model.RestResponse;
import com.AeonAssesment.enums.ErrorCode;
import com.AeonAssesment.model.BorrowerReq;
import com.AeonAssesment.model.BorrowerRespDTO;
import com.AeonAssesment.model.BorrowingReq;
import com.AeonAssesment.model.BorrowingRespDTO;
import com.AeonAssesment.service.BorrowingService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class BorrowingControllerTest {

    @Mock
    private BorrowingService borrowingService;

    private BorrowingController borrowingController;

    @BeforeEach
    private void setup() {
        borrowingController = new BorrowingController(borrowingService);
    }

    @Test
    public void givenValidRequestAndActionTypeWhenProcessCreateReturnResult()
    {
        BorrowingRespDTO borrowingRespDTO = new BorrowingRespDTO();
        borrowingRespDTO.setBorrowerName("RandomName");
        borrowingRespDTO.setBorrowerEmail("test@gmail.com");
        borrowingRespDTO.setBookTitle("RandomTitle");
        borrowingRespDTO.setISBN("9788444603377");
        borrowingRespDTO.setBorrowedDate(OffsetDateTime.now());

        BorrowingReq borrowingReq = new BorrowingReq();
        borrowingReq.setBorrowerName("RandomName");
        borrowingReq.setBorrowerEmail("test@gmail.com");
        borrowingReq.setISBN("9788444603377");

        when(borrowingService.processBorrowing(ActionType.CREATE,borrowingReq)).thenReturn(borrowingRespDTO);
        RestResponse<BorrowingRespDTO> borrowingRespDTORestResponse = borrowingController.process(ActionType.CREATE,borrowingReq);

        verify(borrowingService,times(1)).processBorrowing(any(),any());
        Assertions.assertEquals(borrowingRespDTORestResponse.getData(),borrowingRespDTO);
    }

    @Test
    public void givenValidRequestAndActionTypeWhenProcessUpdateReturnResult()
    {
        BorrowingRespDTO borrowingRespDTO = new BorrowingRespDTO();
        borrowingRespDTO.setUpdateMessage("Return Success");

        BorrowingReq borrowingReq = new BorrowingReq();
        borrowingReq.setBorrowerName("RandomName");
        borrowingReq.setBorrowerEmail("test@gmail.com");
        borrowingReq.setISBN("9788444603377");
        borrowingReq.setBookId(1);

        when(borrowingService.processBorrowing(ActionType.UPDATE,borrowingReq)).thenReturn(borrowingRespDTO);
        RestResponse<BorrowingRespDTO> borrowerRespDTORestResponse = borrowingController.process(ActionType.UPDATE,borrowingReq);

        verify(borrowingService,times(1)).processBorrowing(any(),any());
        Assertions.assertEquals(borrowerRespDTORestResponse.getData(),borrowingRespDTO);
    }

    @Test
    public void givenInvalidActionTypeWhenProcessThrowError()
    {
        BorrowingReq borrowingReq = new BorrowingReq();
        borrowingReq.setBorrowerName("RandomName");
        borrowingReq.setBorrowerEmail("test@gmail.com");
        borrowingReq.setISBN("9788444603377");

        when(borrowingService.processBorrowing(ActionType.DELETE,borrowingReq)).thenThrow(new ServiceException(ErrorCode.INVALID_ACTION_TYPE.toString()));
        Assertions.assertThrows(ServiceException.class, ()-> borrowingController.process(ActionType.DELETE,borrowingReq));

        verify(borrowingService,times(1)).processBorrowing(any(),any());
    }

    @Test
    public void givenInvalidReqWhenProcessCreateThrowError()
    {
        BorrowingReq borrowingReq = new BorrowingReq();
        borrowingReq.setBorrowerName(null);
        borrowingReq.setBorrowerEmail(null);
        borrowingReq.setISBN(null);

        Assertions.assertThrows(BaseException.class, ()-> borrowingController.process(ActionType.CREATE,borrowingReq));
        verify(borrowingService,times(0)).processBorrowing(any(),any());
    }

    @Test
    public void givenInvalidReqWhenProcessUpdateThrowError()
    {
        BorrowingReq borrowingReq = new BorrowingReq();
        borrowingReq.setBorrowerName("RandomName");
        borrowingReq.setBorrowerEmail("test@gmail.com");
        borrowingReq.setISBN("9788444603377");
        borrowingReq.setBookId(null);

        when(borrowingService.processBorrowing(ActionType.UPDATE,borrowingReq)).thenThrow(new ServiceException(ErrorCode.INVALID_MISSING_BOOKID.toString()));
        Assertions.assertThrows(ServiceException.class, ()-> borrowingController.process(ActionType.UPDATE,borrowingReq));
        verify(borrowingService,times(1)).processBorrowing(any(),any());
    }
}
