package com.github.youssefwadie.ors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.youssefwadie.ors.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
