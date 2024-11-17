package com.AeonAssesment.controller;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.common.exception.BaseException;
import com.AeonAssesment.common.model.RestResponse;
import com.AeonAssesment.model.BorrowerRespDTO;
import com.AeonAssesment.model.BorrowingReq;
import com.AeonAssesment.model.BorrowingRespDTO;
import com.AeonAssesment.service.BorrowingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/borrowing")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping(value="/{actionType}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "To process borrowing action")
    public RestResponse<BorrowingRespDTO> process(@PathVariable("actionType") ActionType actionType, @RequestBody BorrowingReq borrowingReq)
    {
        BorrowingRespDTO borrowingRespDTO = borrowingService.processBorrowing(actionType,borrowingReq);
        return RestResponse.success(borrowingRespDTO);
    }
}
