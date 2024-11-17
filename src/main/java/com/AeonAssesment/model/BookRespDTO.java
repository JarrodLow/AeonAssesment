package com.AeonAssesment.model;

import com.AeonAssesment.data.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookRespDTO {

    private String title;
    private String author;
    private String ISBN;

    public static BookRespDTO fromEntityToDTO(Book book)
    {
        BookRespDTO bookRespDTO = new BookRespDTO();
        bookRespDTO.setAuthor(book.getAuthor());
        bookRespDTO.setISBN(book.getISBN());
        bookRespDTO.setTitle(book.getTitle());
        return bookRespDTO;
    }
}
