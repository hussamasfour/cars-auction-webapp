package com.hussam.carsAuction.repositories;

import com.hussam.carsAuction.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findUserByEmail(String email);
}
