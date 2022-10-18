package com.hussam.carsAuction;

import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testFindAllByAuctionEndGreaterThanEqual_success(){
        Car car1 = new Car();

    }

    @Test
    public void testExistsByVinNumber_success(){
        Car car = new Car();
        car.setVinNumber("12345678");
        car.setMake("Audi");
        car.setModel("Q5");
        car.setFuelCapacity(13.3);
        car.setNumberOfSeats(5);
        car.setYear(2022);
        car.setFuelType("Gas");
        car.setImagesLink("img.jpg");
        carRepository.save(car);

        Assertions.assertTrue(carRepository.existsByVinNumber("12345678"));
    }
}
