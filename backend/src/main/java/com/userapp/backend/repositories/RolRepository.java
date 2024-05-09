package com.userapp.backend.repositories;

import com.userapp.backend.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends CrudRepository<Role,Long> {

    Optional<Role> findByName(String name);
}
