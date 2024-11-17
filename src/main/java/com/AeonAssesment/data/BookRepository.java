package com.AeonAssesment.data;

import com.AeonAssesment.model.AvailableBookCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "SELECT * from book where ISBN = ?1 and sts_cd ='A'",nativeQuery = true)
    Optional<List<Book>> retrieveBookListByISBN(String ISBN);

    @Query(value = "SELECT ISBN from book where title = ?1 and author = ?2 and sts_cd ='A'",nativeQuery = true)
    Optional<List<String>> findByTitleAndAuthor(String title , String author);

    @Query(value = "SELECT * from book where sts_cd ='A'" , nativeQuery = true)
    Optional<List<Book>> retrieveBookList();

    @Query(value = "SELECT count(id) from book where ISBN = ?1 and sts_cd ='A'", nativeQuery = true)
    BigInteger countBookAmount(String ISBN);

    @Query(value = "SELECT id from book where sts_cd ='A' and ISBN=?1 " , nativeQuery = true)
    List<Integer> retrieveIdBookListByISBN(String ISBN);
}
