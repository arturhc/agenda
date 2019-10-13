package com.sbcourse.agenda.controller;

import com.sbcourse.agenda.dto.ReservationRequest;
import com.sbcourse.agenda.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity listReservations() {
        return ResponseEntity.ok(reservationService.listReservations());
    }

    @PostMapping
    public ResponseEntity createReservation(@RequestBody ReservationRequest reservation) {

        reservationService.createReservation(reservation);

        return ResponseEntity.ok("Reservacion creada correctamente.");
    }

}
