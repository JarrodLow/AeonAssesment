package com.AeonAssesment.data;

import com.AeonAssesment.model.AvailableBookCount;
import com.AeonAssesment.model.BorrowingRespDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Optional;

public interface BookDetailRepository  extends JpaRepository<BookDetail,Integer> {


    @Query(value = "SELECT count(bd.book_ID) AS borrowedBook FROM book b " +
            "RIGHT JOIN book_detail bd on b.id = bd.book_ID " +
            "where b.ISBN = ?1 and b.sts_cd ='A' and bd.sts_cd ='A'", nativeQuery = true)
    BigInteger findByISBNWhereBookAndDetailStatusActive(String ISBN);

    @Query(value = " SELECT count(bd.id) FROM book b RIGHT JOIN" +
            " book_detail bd on b.id = bd.book_ID where " +
            "b.ISBN = ?1 and b.sts_cd ='A' and bd.sts_cd ='A' and bd.book_ID = ?2", nativeQuery = true)
    Integer findByISBNWhereBookAndDetailStatusActiveRetrieveTitleID( String ISBN, Integer bookId);

    @Query(value = " SELECT bd.id FROM book b RIGHT JOIN" +
            " book_detail bd on b.id = bd.book_ID LEFT JOIN borrower br on bd.borrower_ID = br.id where " +
            "br.name = ?1 and br.email = ?2 and b.ISBN = ?3 and b.sts_cd ='A' and bd.sts_cd ='A' and bd.book_ID = ?4", nativeQuery = true)
    Integer findByBorrowerNameEmailAndISBNWhereStatusActive(String name, String email, String ISBN, Integer bookID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book_detail SET sts_cd = 'N' WHERE id = ?1", nativeQuery = true)
    int returnBook(Integer borrowingId);
}
