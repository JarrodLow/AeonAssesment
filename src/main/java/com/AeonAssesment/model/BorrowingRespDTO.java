package com.AeonAssesment.model;

import com.AeonAssesment.data.BookDetail;
import com.AeonAssesment.data.Borrower;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowingRespDTO {

    private String borrowerName;
    private String borrowerEmail;
    private String bookTitle;
    private String ISBN;
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private OffsetDateTime borrowedDate;
    private String updateMessage;

    public static BorrowingRespDTO fromEntityToDTO(BookDetail bookDetail, AvailableBookCount availableBookCount, BorrowingReq borrower)
    {
        BorrowingRespDTO borrowingRespDTO = new BorrowingRespDTO();
        borrowingRespDTO.setBorrowerName(borrower.getBorrowerName());
        borrowingRespDTO.setBorrowerEmail(borrower.getBorrowerEmail());
        borrowingRespDTO.setBookTitle(availableBookCount.getBookTitle());
        borrowingRespDTO.setISBN(borrower.getISBN());
        borrowingRespDTO.setBorrowedDate(bookDetail.getCreatedTime());
        return borrowingRespDTO;
    }
}
