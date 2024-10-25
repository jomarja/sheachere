package com.parkingapplication.demoApplication.repo;

import com.parkingapplication.demoApplication.data.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;



public interface userRepository extends JpaRepository<user, Long> {

    Optional<user> findByName(String username);

    Optional<user> findByEmail(String email);


}