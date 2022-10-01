package com.hussam.carsAuction.repository;

import com.hussam.carsAuction.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findAllByAuctionEndGreaterThanEqual(Date date);

    @Query("SELECT c FROM car c WHERE " +
            "c.make LIKE CONCAT('%' ,:query, '%') " +
            " OR c.model LIKE CONCAT('%', :query, '%')" +
            " OR c.vinNumber LIKE :query")
    List<Car> searchCars(String query);
}
