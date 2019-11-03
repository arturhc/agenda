package com.sbcourse.agenda.service;

import com.sbcourse.agenda.dto.ReservationInfo;
import com.sbcourse.agenda.dto.ReservationRequest;
import com.sbcourse.agenda.dto.ReservationUpdateRequest;
import com.sbcourse.agenda.persistence.model.ProductService;
import com.sbcourse.agenda.persistence.model.Reservation;
import com.sbcourse.agenda.persistence.repository.ReservationRepository;
import com.sbcourse.agenda.persistence.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
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

        ProductService productService = serviceRepository.findById(reservation.getProductServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Producto inexistente"));

        Reservation reservationEntity = new Reservation();

        LocalDateTime startDate = reservation.getStartDate().toLocalDateTime();

        ChronoUnit durationType = ChronoUnit.valueOf(productService.getDurationType());
        LocalDateTime endDate = startDate.plus(productService.getDuration(), durationType);

        reservationEntity.setProductService(productService);
        reservationEntity.setCellphone(reservation.getCellphone());
        reservationEntity.setEmail(reservation.getEmail());
        reservationEntity.setFullName(reservation.getFullName());
        reservationEntity.setStartDate(startDate);
        reservationEntity.setEndDate(endDate);

        reservationRepository.save(reservationEntity);
    }

    public void updateReservation(long id, ReservationUpdateRequest reservation) throws  Exception{

        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (!optionalReservation.isPresent())
            throw new Exception("No se ha encontrado la reservación");

        ProductService productService = serviceRepository.findById(reservation.getProductServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Producto inexistente"));

        LocalDateTime startDate = reservation.getStartDate().toLocalDateTime();

        ChronoUnit durationType = ChronoUnit.valueOf(productService.getDurationType());
        LocalDateTime endDate = startDate.plus(productService.getDuration(), durationType);

        Reservation reservationEntity = optionalReservation.get();

        reservationEntity.setStartDate(startDate);
        reservationEntity.setEndDate(endDate);

        reservationEntity.setEmail(reservation.getEmail());
        reservationEntity.setCellphone(reservation.getCellphone());
        reservationEntity.setFullName(reservation.getFullName());

        reservationRepository.save(reservationEntity);
    }

    public void deleteReservation(Long id) throws Exception {

        Optional<Reservation> reservation = reservationRepository.findById(id);

        if (!reservation.isPresent())
            throw new Exception("No se encontró la reservación");

        Reservation reservationEntity = reservation.get();
        reservationEntity.setDeleted(true);

        reservationRepository.save(reservationEntity);
    }

}
