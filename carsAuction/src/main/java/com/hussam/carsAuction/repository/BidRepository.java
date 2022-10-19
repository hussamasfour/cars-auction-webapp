package com.hussam.carsAuction.repository;

import com.hussam.carsAuction.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long> {

   @Query(value = "select * from bid as b , car as c where b.car_id = ? order By b.amount DESC limit 1 ", nativeQuery = true)
   Bid findBidWithHighest(Long car_Id);
}
