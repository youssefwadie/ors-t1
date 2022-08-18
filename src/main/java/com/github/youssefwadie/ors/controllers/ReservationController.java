package com.github.youssefwadie.ors.controllers;

import com.github.youssefwadie.ors.entities.ClassType;
import com.github.youssefwadie.ors.entities.Reservation;
import com.github.youssefwadie.ors.entities.User;
import com.github.youssefwadie.ors.repositories.ReservationRepository;
import com.github.youssefwadie.ors.security.ORSUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final ReservationRepository repository;

    @PostMapping("/reservation/save")
    public String saveReservation(Reservation reservation, RedirectAttributes attributes) {
        User loggedInUser = getLoggedInUser();
        reservation.setRegisteredUser(loggedInUser);
        repository.save(reservation);
        attributes.addFlashAttribute("message", "Reservation registered");
        return "redirect:/reservation";
    }

    @GetMapping("/reservation/new")
    public ModelAndView addNewReservation() {
        Map<String, Object> model = new HashMap<>();
        Reservation reservation = new Reservation();
        model.put("trainNumber", 50);
        model.put("trainName", "Train Name");
        model.put("classTypes", ClassType.values());
        model.put("reservation", reservation);
        return new ModelAndView("reservation_form", model);
    }

    @GetMapping("/reservation")
    public ModelAndView listAllReservations() {
        User loggedInUser = getLoggedInUser();
        Map<String, Object> model = new HashMap<>();
        model.put("reservations", repository.findAllByRegisteredUserId(loggedInUser.getId()));
        return new ModelAndView("reservations", model);
    }

    @GetMapping("reservation/{id:\\d+}")
    public String getReservationById(@PathVariable("id") Long id, Model model) {
        Optional<Reservation> optionalReservation = repository.findById(id);
        if (optionalReservation.isEmpty()) {
            return "redirect:/reservation";
        }
        model.addAttribute("reservation", optionalReservation.get());
        return "reservation";
    }


    @PostMapping("reservation/cancel/{id:\\d+}")
    public String cancelReservation(@PathVariable("id") Long id, RedirectAttributes attributes) {
        User loggedInUser = getLoggedInUser();
        Optional<Reservation> optionalReservation = repository.findById(id);
        if (optionalReservation.isEmpty()) {
            return "redirect:/reservation";
        }
        Reservation reservation = optionalReservation.get();
        Long registeredUserId = reservation.getRegisteredUser().getId();
        if (!registeredUserId.equals(loggedInUser.getId())) {
            return "redirect:/reservation";
        }

        repository.delete(reservation);
        attributes.addFlashAttribute(
                "message", "The reservation with PNR %d has been deleted".formatted(reservation.getId()));
        return "redirect:/reservation";
    }

    private User getLoggedInUser() {
        return ((ORSUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}
