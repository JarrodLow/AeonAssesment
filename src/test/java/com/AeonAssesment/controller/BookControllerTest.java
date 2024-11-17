package com.AeonAssesment.controller;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.common.exception.BaseException;
import com.AeonAssesment.common.model.RestResponse;
import com.AeonAssesment.enums.ErrorCode;
import com.AeonAssesment.model.BookReq;
import com.AeonAssesment.model.BookRespDTO;
import com.AeonAssesment.service.BookService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookControllerTest {

    @Mock
    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    private void setup() {
        bookController = new BookController(bookService);
    }

    @Test
    public void givenValidRequestAndActionTypeWhenProcessReturnResult()
    {
        BookRespDTO bookRespDTO = new BookRespDTO();
        bookRespDTO.setTitle("sample");
        bookRespDTO.setAuthor("sample");
        bookRespDTO.setISBN("9788444603377");

        BookReq bookReq = new BookReq();
        bookReq.setAuthor("sample");
        bookReq.setTitle("sample");

        when(bookService.processBook(ActionType.CREATE,bookReq)).thenReturn(bookRespDTO);
        RestResponse<BookRespDTO> bookRespDTORestResponse = bookController.process(ActionType.CREATE,bookReq);

        verify(bookService,times(1)).processBook(any(),any());
        Assertions.assertEquals(bookRespDTORestResponse.getData(),bookRespDTO);

    }

    @Test
    public void givenInvalidActionTypeWhenProcessThrowError()
    {
        BookReq bookReq = new BookReq();
        bookReq.setAuthor("sample");
        bookReq.setTitle("sample");

        when(bookService.processBook(ActionType.UPDATE,bookReq)).thenThrow(new ServiceException(ErrorCode.INVALID_ACTION_TYPE.toString()));
        Assertions.assertThrows(ServiceException.class, ()-> bookController.process(ActionType.UPDATE,bookReq));

        verify(bookService,times(1)).processBook(any(),any());

    }

    @Test
    public void givenInvalidReqWhenProcessThrowError()
    {
        BookReq bookReq = new BookReq();
        bookReq.setTitle(null);
        bookReq.setAuthor(null);

        Assertions.assertThrows(BaseException.class, ()-> bookController.process(ActionType.CREATE,bookReq));
        verify(bookService,times(0)).processBook(any(),any());

    }

    @Test
    public void givenNothingWhenRetrieveBookListReturnList()
    {
        BookRespDTO bookRespDTO = new BookRespDTO();
        bookRespDTO.setTitle("sample");
        bookRespDTO.setAuthor("sample");
        bookRespDTO.setISBN("9788444603377");

        List<BookRespDTO> bookRespDTOList = new ArrayList<>();
        bookRespDTOList.add(bookRespDTO);

        when(bookService.retrieveBookList()).thenReturn(bookRespDTOList);
        RestResponse<List<BookRespDTO>> bookRespDTORestResponse = bookController.retrieveBookList();

        verify(bookService,times(1)).retrieveBookList();
        Assertions.assertEquals(bookRespDTORestResponse.getData(),bookRespDTOList);

    }

    @Test
    public void givenNothingWhenRetrieveBookListReturnEmptyList()
    {

        List<BookRespDTO> bookRespDTOList = new ArrayList<>();

        when(bookService.retrieveBookList()).thenReturn(bookRespDTOList);
        RestResponse<List<BookRespDTO>> bookRespDTORestResponse = bookController.retrieveBookList();

        verify(bookService,times(1)).retrieveBookList();
        Assertions.assertEquals(bookRespDTORestResponse.getData(),bookRespDTOList);

    }
}
