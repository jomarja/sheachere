package com.parkingapplication.demoApplication.service;

import com.parkingapplication.demoApplication.data.reservation;
import com.parkingapplication.demoApplication.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private static ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public static List<reservation> getReservationsByUserID(Long userID) {
        return reservationRepository.findByUserUserID(userID);
    }

}