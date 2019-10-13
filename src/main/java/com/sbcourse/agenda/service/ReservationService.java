package com.sbcourse.agenda.service;

import com.sbcourse.agenda.dto.ReservationInfo;
import com.sbcourse.agenda.dto.ReservationRequest;
import com.sbcourse.agenda.persistence.model.Reservation;
import com.sbcourse.agenda.persistence.repository.ReservationRepository;
import com.sbcourse.agenda.persistence.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ReservationInfo> listReservations() {
        return reservationRepository.findAll().stream()
                .map(reservation -> {
                    return modelMapper.map(reservation, ReservationInfo.class);
                }).collect(Collectors.toList());
    }

    public void createReservation(ReservationRequest reservation) {

        com.sbcourse.agenda.persistence.model.Service service = serviceRepository.findById(reservation.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Servicio inexistente"));

        Reservation reservationEntity = new Reservation();

        reservationEntity.setService(service);
        reservationEntity.setCellphone(reservation.getCellphone());
        reservationEntity.setEmail(reservation.getEmail());
        reservationEntity.setFullName(reservation.getFullName());
        reservationEntity.setStartDate(reservation.getStartDate().toLocalDateTime());
        reservationEntity.setEndDate(reservation.getEndDate().toLocalDateTime());

        reservationRepository.save(reservationEntity);
    }

}
