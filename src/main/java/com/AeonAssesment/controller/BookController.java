package com.AeonAssesment.controller;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.common.model.RestResponse;
import com.AeonAssesment.model.BookReq;
import com.AeonAssesment.model.BookRespDTO;
import com.AeonAssesment.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value="/{actionType}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "To add or update book information")
    public RestResponse<BookRespDTO> process(@PathVariable("actionType")ActionType actionType, @RequestBody BookReq bookRequest)
    {
            BookRespDTO bookRespDTO = bookService.processBook(actionType,bookRequest);
            return RestResponse.success(bookRespDTO);
    }

    @GetMapping(value = "/retrieve")
    @Operation(summary = "To retrieve list of book")
    public RestResponse<List<BookRespDTO>> retrieveBookList ()
    {
        return RestResponse.success(bookService.retrieveBookList());
    }

}
