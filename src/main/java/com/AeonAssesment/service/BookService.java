package com.AeonAssesment.service;

import com.AeonAssesment.common.enums.ActionType;
import com.AeonAssesment.data.Book;
import com.AeonAssesment.data.BookRepository;
import com.AeonAssesment.enums.ErrorCode;
import com.AeonAssesment.model.BookReq;
import com.AeonAssesment.model.BookRespDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public BookRespDTO processBook(ActionType actionType, BookReq bookReq)
    {
        if(actionType.equals(ActionType.CREATE))
        {
            try
            {
                Boolean ISBNValidation = false;
                Book newBook = objectMapper.convertValue(bookReq,Book.class);
                //first generate ISBN
                String generatedISBN = ISBNGenerator();
                //third if exist in DB then check title and name = or no if not equal regenerate one
                newBook.setISBN(generatedISBN);

                Optional<List<String>> retrievedISBN = bookRepository.findByTitleAndAuthor(newBook.getTitle(), newBook.getAuthor());

                if(!retrievedISBN.get().isEmpty() && retrievedISBN.get().contains(generatedISBN))
                {
                    newBook.setISBN(generatedISBN);
                    return BookRespDTO.fromEntityToDTO(bookRepository.saveAndFlush(newBook));
                }
                else if(!retrievedISBN.get().isEmpty() && !retrievedISBN.get().contains(generatedISBN))
                {
                    newBook.setISBN(generatedISBN);
                    return BookRespDTO.fromEntityToDTO(bookRepository.saveAndFlush(newBook));
                }

                // author and title not exist && Check ISBN exist or no
                Optional<List<Book>> beforeLoop = bookRepository.retrieveBookListByISBN(generatedISBN);
                if(beforeLoop.isEmpty())
                {
                    newBook.setISBN(generatedISBN);
                    return BookRespDTO.fromEntityToDTO(bookRepository.saveAndFlush(newBook));

                }
                while (!ISBNValidation) {

                    String responsiveISBN = ISBNGenerator();
                    Optional<List<Book>> checkBook = bookRepository.retrieveBookListByISBN(responsiveISBN);

                    if(checkBook.get().isEmpty())
                    {
                        newBook.setISBN(responsiveISBN);
                        ISBNValidation =true;
                    }
                }
                return BookRespDTO.fromEntityToDTO(bookRepository.saveAndFlush(newBook));
            }
            catch(RuntimeException ex)
            {
                throw new RuntimeException(ErrorCode.SERVICE_EXCEPTION.toString() + ex.getMessage());
            }
        }
        throw new ServiceException(ErrorCode.INVALID_ACTION_TYPE.toString());
    }

    public List<BookRespDTO> retrieveBookList()
    {
        Optional<List<Book>> bookLists = bookRepository.retrieveBookList();
        if(bookLists.get().isEmpty())
        {
            return new ArrayList<>();
        }
        List<Book> bookList = bookLists.get();
        List<BookRespDTO> bookRespDTOS = bookList.stream().map(
                x-> new BookRespDTO(x.getTitle(), x.getAuthor(), x.getISBN())
        ).toList();
        return bookRespDTOS;
    }

    private String ISBNGenerator()
    {
        StringBuilder isbn = new StringBuilder("978");
        for (int i = 0; i < 9; i++) {
            isbn.append(new Random().nextInt(10));
        }

        int checksum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            checksum += (i % 2 == 0) ? digit : 3 * digit;
        }
        checksum = (10 - (checksum % 10)) % 10;

        isbn.append(checksum);
        return isbn.toString();
    }

}
