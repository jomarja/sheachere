package com.parkingapplication.demoApplication.repo;

import com.parkingapplication.demoApplication.data.parkingspot;
import com.parkingapplication.demoApplication.data.reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<reservation, Long> {
    List<reservation> findByUserUserID(Long userID);


}
