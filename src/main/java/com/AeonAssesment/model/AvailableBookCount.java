package com.AeonAssesment.model;

import com.AeonAssesment.data.Book;
import com.AeonAssesment.data.BookDetail;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AvailableBookCount {

    private Integer bookId;
    private String bookTitle;
    @JsonProperty(defaultValue = "0")
    private BigInteger bookCount;
    @JsonProperty(defaultValue = "0")
    private BigInteger borrowedBook;

    public static AvailableBookCount fromEntityObjectToDTOCount(BigInteger bookCount, BigInteger borrowingCount)
    {
        AvailableBookCount availableBookCount = new AvailableBookCount();
        availableBookCount.setBookCount(bookCount);
        availableBookCount.setBorrowedBook(borrowingCount);
        return availableBookCount;
    }

    public static AvailableBookCount fromEntityObjectToDTOTitle(Book book, Integer bookId)
    {
        AvailableBookCount availableBookCount = new AvailableBookCount();
        availableBookCount.setBookId(bookId);
        availableBookCount.setBookTitle(book.getTitle());
        return availableBookCount;
    }
}
