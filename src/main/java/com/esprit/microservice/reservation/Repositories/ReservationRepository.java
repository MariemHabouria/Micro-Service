package com.esprit.microservice.reservation.Repositories;

import com.esprit.microservice.reservation.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClientName(String clientName);
}
