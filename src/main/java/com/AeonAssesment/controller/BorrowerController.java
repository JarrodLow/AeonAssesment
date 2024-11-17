package com.AeonAssesment.controller;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.common.model.RestResponse;
import com.AeonAssesment.model.BorrowerReq;
import com.AeonAssesment.model.BorrowerRespDTO;
import com.AeonAssesment.service.BorrowerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/borrower")
@RequiredArgsConstructor
public class BorrowerController {

    private final BorrowerService borrowerService;

    @PostMapping(value="/{actionType}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "To add or update borrower information")
    public RestResponse<BorrowerRespDTO> process(@PathVariable("actionType") ActionType actionType, @RequestBody BorrowerReq borrowerRequest)
    {
        BorrowerRespDTO borrowerRespDTO = borrowerService.processBorrower(actionType,borrowerRequest);
        return RestResponse.success(borrowerRespDTO);
    }
}
