package com.parkingapplication.demoApplication.repo;

import com.parkingapplication.demoApplication.data.parkinglot;
import com.parkingapplication.demoApplication.data.user;
import org.springframework.data.repository.CrudRepository;

public interface ParkingLotRepository extends CrudRepository<parkinglot,Long> {
}
