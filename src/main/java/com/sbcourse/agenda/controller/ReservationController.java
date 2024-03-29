package com.sbcourse.agenda.controller;

import com.sbcourse.agenda.dto.ReservationRequest;
import com.sbcourse.agenda.dto.ReservationUpdateRequest;
import com.sbcourse.agenda.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PutMapping("/{reservationId}")
    public ResponseEntity updateReservation(@PathVariable Long reservationId,
                                            @Valid @RequestBody ReservationUpdateRequest reservation) {
        try {
            reservationService.updateReservation(reservationId, reservation);
        } catch (Exception ex) {
            return ResponseEntity.ok(ex.getMessage());
        }

        return ResponseEntity.ok("Reservacion actualizada correctamente.");
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity deleteReservation(@PathVariable Long reservationId) {

        try {
            reservationService.deleteReservation(reservationId);
        } catch (Exception ex) {
            return ResponseEntity.ok(ex.getMessage());
        }

        return ResponseEntity.ok("Reservacion eliminada correctamente.");

    }

}
