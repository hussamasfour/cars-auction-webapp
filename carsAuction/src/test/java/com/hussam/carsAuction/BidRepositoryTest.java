package com.hussam.carsAuction;

import com.hussam.carsAuction.entity.Bid;
import com.hussam.carsAuction.entity.Car;
import com.hussam.carsAuction.entity.User;
import com.hussam.carsAuction.repository.BidRepository;
import com.hussam.carsAuction.repository.CarRepository;
import com.hussam.carsAuction.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BidRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindHighestBid() throws ParseException {

        Car car = new Car();
        car.setId(1L);
        car.setVinNumber("12345678");
        car.setMake("Audi");
        car.setModel("Q5");
        car.setFuelCapacity(13.3);
        car.setNumberOfSeats(5);
        car.setYear(2022);
        car.setFuelType("Gas");
        car.setImagesLink("img.jpg");

        String date_string = "26-09-1989";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);
        car.setAuctionEnd(date);

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setPassword("12345678");
        userRepository.save(user);

        Bid bid1 = new Bid();
        bid1.setBidDate(new Date());
        bid1.setAmount(1000.10);
        bid1.setUser(user);
        bid1.setCar(car);


        Bid bid2 = new Bid();
        bid2.setBidDate(new Date());
        bid2.setAmount(2000.10);
        bid2.setCar(car);
        bid2.setUser(user);

        Set<Bid> bids = new HashSet<>();
        bids.add(bid1);
        bids.add(bid2);

        car.setBids(bids);

        carRepository.save(car);
        Assertions.assertEquals(bid2.getAmount(), bidRepository.findHighestBid(car.getId()));
    }
}
