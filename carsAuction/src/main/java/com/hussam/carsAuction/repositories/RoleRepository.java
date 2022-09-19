package com.hussam.carsAuction.repositories;

import com.hussam.carsAuction.models.Role;
import com.hussam.carsAuction.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByType(Type type);
}
