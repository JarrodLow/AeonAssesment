package com.AeonAssesment.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BorrowerRepository  extends JpaRepository<Borrower,Integer> {

    @Query(value ="Select * from borrower where email = ?1 and name =?2", nativeQuery = true)
    Optional<Borrower>retrieveUserByEmailAndName(String email, String name);

    @Query(value ="Select * from borrower where email = ?1", nativeQuery = true)
    Optional<Borrower>retrieveUserByEmail(String email);

}
