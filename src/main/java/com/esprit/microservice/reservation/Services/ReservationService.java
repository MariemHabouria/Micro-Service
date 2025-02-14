package com.esprit.microservice.reservation.Services;


import com.esprit.microservice.reservation.Entities.Reservation;
import com.esprit.microservice.reservation.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation newReservation) {
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setClientName(newReservation.getClientName());
            reservation.setActivity(newReservation.getActivity());
            reservation.setReservationDate(newReservation.getReservationDate());
            reservation.setConfirmed(newReservation.isConfirmed());
            return reservationRepository.save(reservation);
        }).orElse(null);
    }

    public String deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return "Réservation supprimée";
        }
        return "Réservation non trouvée";
    }
}
