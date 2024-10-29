package com.parkingapplication.demoApplication.controllers;

import com.parkingapplication.demoApplication.data.parkinglot;
import com.parkingapplication.demoApplication.data.parkingspot;
import com.parkingapplication.demoApplication.data.reservation;
import com.parkingapplication.demoApplication.data.user;
import com.parkingapplication.demoApplication.repo.ParkingLotRepository;
import com.parkingapplication.demoApplication.repo.ParkingSpotRepository;
import com.parkingapplication.demoApplication.repo.ReservationRepository;
import com.parkingapplication.demoApplication.repo.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ReserveController {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private userRepository userRepository;

    @GetMapping("/reservation")
    public String reservationPage(Model model) {
        Iterable<parkinglot> parkingLots = parkingLotRepository.findAll();
        model.addAttribute("parkinglots", parkingLots);
        return "Reservation";
    }
    @PostMapping("/reservation")
    public String postReserve(
            @RequestParam Long parkinglot,
            @RequestParam(required = false, defaultValue = "false") Boolean evCharging,
            @RequestParam String date,
            @RequestParam String start_time,
            @RequestParam String end_time,
            Model model,
            Principal principal) {

        try {
            // Check if a parking lot was selected
            if (parkinglot == null) {
                model.addAttribute("message", "Parking lot is required.");
                return "Reservation";
            }else {
                System.out.println("Parking Lot ID: " + parkinglot);
            }

            // Step 1: Get logged-in user by email from principal
            String userEmail = principal.getName();
            Optional<user> loggedInUser = userRepository.findByName(userEmail);

            if (loggedInUser.isEmpty()) {
                model.addAttribute("message", "User not found. Please log in.");
                return "Reservation";
            }

            user user = loggedInUser.get();

            // Convert date and time parameters to LocalDateTime
            LocalDate selectedDate = LocalDate.parse(date);
            LocalTime startTime = LocalTime.parse(start_time);
            LocalTime endTime = LocalTime.parse(end_time);
            LocalDateTime startDateTime = LocalDateTime.of(selectedDate, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(selectedDate, endTime);

            // Handle evCharging parameter gracefully
            boolean evChargingValue = evCharging != null && evCharging;

            // Find the parking lot and its location
            Optional<parkinglot> selectedParkingLot = parkingLotRepository.findById(parkinglot);
            if (selectedParkingLot.isEmpty()) {
                model.addAttribute("message", "Invalid parking lot selected.");
                return "Reservation";
            }

            String lotLocation = selectedParkingLot.get().getLocation();

            // Find available parking spots in the selected parking lot
            List<parkingspot> availableSpots = parkingSpotRepository.findAvailableSpots(parkinglot, evChargingValue, startDateTime, endDateTime);

            if (!availableSpots.isEmpty()) {
                // Choose the first available spot
                parkingspot selectedSpot = availableSpots.get(0);

                // Create a new reservation
                reservation newReservation = new reservation();
                newReservation.setUser(user); // Set the actual logged-in user
                newReservation.setParkingspot(selectedSpot);
                newReservation.setStartTime(startDateTime);
                newReservation.setEndTime(endDateTime);
                newReservation.setPrice(selectedSpot.getPrice());
                newReservation.setLotLocation(lotLocation); // Set the lot location

                // Save the reservation and update availability status of the parking spot
                selectedSpot.setAvailability(false);  // Mark the spot as unavailable
                reservationRepository.save(newReservation);
                parkingSpotRepository.save(selectedSpot); // Save the updated spot

                model.addAttribute("message", "Reservation successful!");
                return "SuccessReservation";
            } else {
                model.addAttribute("message", "No available parking spots.");
                return "Reservation";
            }

        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            model.addAttribute("message", "An unexpected error occurred: " + e.getMessage());
            return "error";
        }

    }

    @Controller
    public class CustomErrorController implements ErrorController {
        @RequestMapping("/error")
        public String handleError() {
            return "error"; // Point to your custom error HTML page
        }
    }


}
