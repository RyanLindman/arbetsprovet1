package com.lindman.repository;

import com.lindman.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {


    List<Listing> findByTitleOrDescription(String title, String description) ;


    @Query("SELECT l FROM Listing l WHERE l.createdAt = :targetDate")
    List<Listing> findByDate(@Param("targetDate") Date targetDate);




    List<Listing> findByCreatedAtAfterAndCreatedAtBetween(String createdAfter, String betweenThis, String andThis);


}
