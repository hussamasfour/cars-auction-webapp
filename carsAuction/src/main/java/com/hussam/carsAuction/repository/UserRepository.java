package com.hussam.carsAuction.repository;

import com.hussam.carsAuction.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findUserByEmail(String email);
}
