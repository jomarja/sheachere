package com.parkingapplication.demoApplication.repo;


import com.parkingapplication.demoApplication.data.parkingspot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<parkingspot, Long> {
    @Query("SELECT ps FROM parkingspot ps WHERE ps.parkinglot.lotID = :lotID AND ps.isEVChargingAvailable = :evCharging " +
            "AND ps.availability = true AND ps.spotID NOT IN (SELECT r.parkingspot.spotID FROM reservation r " +
            "WHERE (r.startTime < :endTime AND r.endTime > :startTime))")
    List<parkingspot> findAvailableSpots(
            @Param("lotID") Long lotID,
            @Param("evCharging") boolean evCharging,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);
}
