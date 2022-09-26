package com.hussam.carsAuction.repository;

import com.hussam.carsAuction.entity.Role;
import com.hussam.carsAuction.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByType(Type type);
}
