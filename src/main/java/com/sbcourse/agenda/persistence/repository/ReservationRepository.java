package com.sbcourse.agenda.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbcourse.agenda.persistence.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {



}
