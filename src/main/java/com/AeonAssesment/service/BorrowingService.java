package com.AeonAssesment.service;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.data.*;
import com.AeonAssesment.enums.ErrorCode;
import com.AeonAssesment.model.AvailableBookCount;
import com.AeonAssesment.model.BookRespDTO;
import com.AeonAssesment.model.BorrowingReq;
import com.AeonAssesment.model.BorrowingRespDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class BorrowingService {

    private final BookDetailRepository bookDetailRepository;
    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;
    @Transactional
    public BorrowingRespDTO processBorrowing(ActionType actionType, BorrowingReq borrowingReq)
    {
        if(actionType.equals(ActionType.CREATE))
        {
            BigInteger bookResult = bookRepository.countBookAmount(borrowingReq.getISBN());
            BigInteger borrowingResult = bookDetailRepository.findByISBNWhereBookAndDetailStatusActive(borrowingReq.getISBN());

            AvailableBookCount availableBookCount = AvailableBookCount.fromEntityObjectToDTOCount(bookResult, borrowingResult);
            if(availableBookCount.getBookCount().equals(BigInteger.ZERO))
            {
                throw new ServiceException(ErrorCode.INVALID_BORROWING_ACTION.toString());
            }
            else if(availableBookCount.getBorrowedBook().compareTo(availableBookCount.getBookCount()) >= 0 )
            {
                throw new ServiceException(ErrorCode.INVALID_NO_BOOK.toString());
            }

            Optional<Borrower> borrowerOptional = borrowerRepository.retrieveUserByEmailAndName(borrowingReq.getBorrowerEmail(),borrowingReq.getBorrowerName());
            if(borrowerOptional.isEmpty())
            {
                throw new ServiceException(ErrorCode.INVALID_NOT_EXIST_USER.toString());
            }

            List<Integer> bookList = bookRepository.retrieveIdBookListByISBN(borrowingReq.getISBN());
            Optional<List<Book>>  rawBookList = bookRepository.retrieveBookListByISBN(borrowingReq.getISBN());
            Book book = objectMapper.convertValue(rawBookList.get().get(0), Book.class);
            Boolean loopCount = false;
            int x=0;
            while(!loopCount)
            {
                Integer isExist = bookDetailRepository.findByISBNWhereBookAndDetailStatusActiveRetrieveTitleID(borrowingReq.getISBN(), bookList.get(x));
                if(isExist == 0)
                {
                    availableBookCount = AvailableBookCount.fromEntityObjectToDTOTitle(book, bookList.get(x));
                    loopCount = true;
                }
                x++;
            }

            BookDetail bookDetail = new BookDetail();
            bookDetail.setBorrowerID(borrowerOptional.get().getId());
            bookDetail.setBookID(availableBookCount.getBookId());

            BookDetail bookDetailResp = bookDetailRepository.saveAndFlush(bookDetail);
            return BorrowingRespDTO.fromEntityToDTO(bookDetailResp,availableBookCount,borrowingReq);
        }
        else if (actionType.equals(ActionType.UPDATE))
        {
            if(borrowingReq.getBookId() != null)
            {
                Integer borrowingId = bookDetailRepository.findByBorrowerNameEmailAndISBNWhereStatusActive
                        (borrowingReq.getBorrowerName(),borrowingReq.getBorrowerEmail(),borrowingReq.getISBN(),borrowingReq.getBookId());
                if(borrowingId != null)
                {
                    BorrowingRespDTO borrowingRespDTO = new BorrowingRespDTO();
                    if(bookDetailRepository.returnBook(borrowingId) == 1)
                    {
                        borrowingRespDTO.setUpdateMessage("Return Success");
                        return borrowingRespDTO;
                    }
                    throw new ServiceException(ErrorCode.INVALID_UPDATE.toString());
                }
                throw new ServiceException(ErrorCode.INVALID_NOT_EXIST_BORROWING.toString());
            }
            throw new ServiceException(ErrorCode.INVALID_MISSING_BOOKID.toString());
        }
        throw new ServiceException(ErrorCode.INVALID_ACTION_TYPE.toString());

    }
}
