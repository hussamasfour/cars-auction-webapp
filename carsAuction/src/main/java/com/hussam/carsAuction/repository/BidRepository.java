package com.hussam.carsAuction.repository;

import com.hussam.carsAuction.entity.Bid;
import com.hussam.carsAuction.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {
   @Query("select max(b.amount) from bid b inner join b.car c where c.id = :car_id")
    Double findHighestBid(Long car_id);
}
