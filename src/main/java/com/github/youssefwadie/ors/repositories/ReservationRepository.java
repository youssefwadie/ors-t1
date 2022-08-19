package com.github.youssefwadie.ors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.youssefwadie.ors.model.Reservation;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.registeredUser.id = ?1")
    List<Reservation> findAllByRegisteredUserId(Long userId);
}
